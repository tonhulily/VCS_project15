package com.example.vcs_project15.presentation.camera

import android.net.Uri
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CameraViewModel
@Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(CameraState())
    val state: StateFlow<CameraState> = _state
    fun updateImage(
        uri: Uri
    ) {
        _state.value =
            _state.value.copy(
                imageUri = uri
            )
    }
    fun toggleFlash() {
        _state.value =
            _state.value.copy(
                isFlashEnabled =
                    !_state.value.isFlashEnabled
            )
    }
    fun switchCamera() {
        _state.value =
            _state.value.copy(
                isFrontCamera =
                    !_state.value.isFrontCamera
            )
    }
}