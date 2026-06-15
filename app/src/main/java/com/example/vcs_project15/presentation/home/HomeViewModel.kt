package com.example.vcs_project15.presentation.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.vcs_project15.domain.usecase.DetectLabelUseCase
import com.example.vcs_project15.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val detectLabelUseCase: DetectLabelUseCase,
    private val searchImageUseCase: SearchImageUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(HomeState())
    private val keywordFlow = MutableStateFlow("")
    val state: StateFlow<HomeState> = _state
    @OptIn(ExperimentalCoroutinesApi::class)
    val images =
        keywordFlow
            .filter {
                it.isNotBlank()
            }
            .flatMapLatest {
                searchImageUseCase(it)
            }
            .cachedIn(
                viewModelScope
            )
    fun setImage(
        uri: Uri,
        base64: String
    ) {
        _state.value =
            _state.value.copy(
                imageUri = uri,
                imageBase64 = base64
            )
    }

    fun detectImage() {
        val imageBase64 =
            _state.value.imageBase64
                ?: return
        viewModelScope.launch {
            try {
                _state.value =
                    _state.value.copy(
                        loading = true
                    )
                val labels =
                    detectLabelUseCase(
                        imageBase64
                    )
                val keyword =
                    labels
                        .maxByOrNull {
                            it.score
                        }
                        ?.description
                        ?: return@launch
                _state.value =
                    _state.value.copy(
                        labels = labels,
                        keyword = keyword,
                        loading = false
                    )
                keywordFlow.value = keyword
            } catch (e: Exception) {
                _state.value =
                    _state.value.copy(
                        loading = false,
                        error = e.message
                    )
            }
        }
    }
}