package com.example.rickandmortyapp.domain.usecase

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend operator fun invoke(): List<Character> {
        return repository.getCharacters()
    }
}