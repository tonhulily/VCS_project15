package com.example.vcs_project15.presentation.home

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.vcs_project15.domain.usecase.DetectWebUseCase
import com.example.vcs_project15.domain.usecase.SearchImageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class HomeViewModel
@Inject constructor(
    private val detectWebUseCase: DetectWebUseCase,
    private val searchImageUseCase: SearchImageUseCase
) : ViewModel() {
    private val _state =
        MutableStateFlow(
            HomeState()
        )
    val state = _state.asStateFlow()
    private val _event = Channel<UiEvent>()
    val event = _event.receiveAsFlow()
    private val queryFlow = MutableStateFlow("")
    @OptIn(ExperimentalCoroutinesApi::class)
    val images =
        queryFlow
            .filter {
                it.isNotBlank()
            }
            .flatMapLatest {
                searchImageUseCase(
                    it
                )
            }
            .cachedIn(
                viewModelScope
            )
    fun setImage(
        uri: Uri,
        base64: String
    ) {
        _state.update {
            it.copy(
                imageUri = uri,
                imageBase64 = base64,
                error = null
            )
        }
    }
    fun detectImage() {
        val imageBase64 =
            _state.value.imageBase64
                ?: return
        viewModelScope.launch {
            try {
                _state.update {
                    it.copy(
                        loading = true,
                        error = null
                    )
                }
                val result =
                    detectWebUseCase(
                        imageBase64
                    )
                _state.update {
                    it.copy(
                        loading = false,
                        searchQuery = result.query,
                        labels = result.entities
                    )
                }
                queryFlow.value = result.query
            } catch (e: Exception) {
                _state.update {
                    it.copy(
                        loading = false,
                        error = e.message
                    )
                }
                _event.send(
                    UiEvent.ShowError(
                        e.message
                            ?: "Unknown error"
                    )
                )
            }
        }
    }
    fun retry() {
        state.value.searchQuery
            ?.let {
                queryFlow.value = it
            }
    }
}