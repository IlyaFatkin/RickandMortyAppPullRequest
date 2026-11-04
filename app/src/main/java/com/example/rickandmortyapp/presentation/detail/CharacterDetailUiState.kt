package com.example.rickandmortyapp.presentation.detail

import com.example.rickandmortyapp.data.model.Character

sealed interface CharacterDetailUiState {

    data object Loading : CharacterDetailUiState

    data class Success(
        val character: Character
    ) : CharacterDetailUiState

    data class Error(
        val message: String
    ) : CharacterDetailUiState
}