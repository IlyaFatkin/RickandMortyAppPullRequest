package com.example.rickandmortyapp.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.api.RetrofitInstance
import com.example.rickandmortyapp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapp.domain.usecase.GetCharactersUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterListViewModel : ViewModel() {

    private val repository = CharacterRepositoryImpl(RetrofitInstance.api)
    private val getCharactersUseCase = GetCharactersUseCase(repository)

    private val _uiState =
        MutableStateFlow<CharacterListUiState>(CharacterListUiState.Loading)

    val uiState: StateFlow<CharacterListUiState> =
        _uiState.asStateFlow()

    init {
        loadCharacters()
    }

    fun loadCharacters() {
        viewModelScope.launch {
            _uiState.value = CharacterListUiState.Loading

            try {
                val characters = getCharactersUseCase()

                _uiState.value = if (characters.isEmpty()) {
                    CharacterListUiState.Empty
                } else {
                    CharacterListUiState.Success(characters)
                }

            } catch (e: Exception) {
                _uiState.value = CharacterListUiState.Error(
                    e.message ?: "Неизвестная ошибка"
                )
            }
        }
    }
}