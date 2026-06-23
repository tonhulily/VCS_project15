package com.example.vcs_project15.presentation.home
sealed interface UiEvent {
    data class ShowError(
        val message: String
    ) : UiEvent
}