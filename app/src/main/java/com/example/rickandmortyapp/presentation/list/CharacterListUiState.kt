package com.example.rickandmortyapp.presentation.list

import com.example.rickandmortyapp.data.model.Character

sealed interface CharacterListUiState {

    data object Loading : CharacterListUiState

    data object Empty : CharacterListUiState

    data class Success(
        val characters: List<Character>
    ) : CharacterListUiState

    data class Error(
        val message: String
    ) : CharacterListUiState
}