package com.example.rickandmortyapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmortyapp.data.api.RetrofitInstance
import com.example.rickandmortyapp.data.repository.CharacterRepositoryImpl
import com.example.rickandmortyapp.domain.usecase.GetCharacterDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CharacterDetailViewModel : ViewModel() {

    private val repository = CharacterRepositoryImpl(RetrofitInstance.api)
    private val getCharacterDetailsUseCase =
        GetCharacterDetailsUseCase(repository)

    private val _uiState =
        MutableStateFlow<CharacterDetailUiState>(
            CharacterDetailUiState.Loading
        )

    val uiState: StateFlow<CharacterDetailUiState> =
        _uiState.asStateFlow()

    fun loadCharacter(id: Int) {
        viewModelScope.launch {

            _uiState.value = CharacterDetailUiState.Loading

            try {
                val character = getCharacterDetailsUseCase(id)

                _uiState.value =
                    CharacterDetailUiState.Success(character)

            } catch (e: Exception) {

                _uiState.value =
                    CharacterDetailUiState.Error(
                        e.message ?: "Неизвестная ошибка"
                    )
            }
        }
    }
}