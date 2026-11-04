package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.CharacterRepository

class GetCharacterDetailsUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(id: Int): Character {
        return repository.getCharacterDetails(id)
    }
}