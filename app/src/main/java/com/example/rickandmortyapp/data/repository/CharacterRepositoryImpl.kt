package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.api.RickAndMortyApi
import com.example.rickandmortyapp.data.model.Character

class CharacterRepositoryImpl(
    private val api: RickAndMortyApi
) : CharacterRepository {

    override suspend fun getCharacters(): List<Character> {
        return api.getCharacters().results
    }

    override suspend fun getCharacterDetails(id: Int): Character {
        return api.getCharacterDetails(id)
    }
}