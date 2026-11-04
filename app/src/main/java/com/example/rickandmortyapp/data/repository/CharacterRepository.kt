package com.example.rickandmortyapp.data.repository

import com.example.rickandmortyapp.data.model.Character

interface CharacterRepository {

    suspend fun getCharacters(): List<Character>

    suspend fun getCharacterDetails(id: Int): Character
}