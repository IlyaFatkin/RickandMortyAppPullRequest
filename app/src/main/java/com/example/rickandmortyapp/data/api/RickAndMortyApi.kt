package com.example.rickandmortyapp.data.api

import com.example.rickandmortyapp.data.model.Character
import com.example.rickandmortyapp.data.model.CharacterResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {

    @GET("character")
    suspend fun getCharacters(): CharacterResponse

    @GET("character/{id}")
    suspend fun getCharacterDetails(
        @Path("id") id: Int
    ): Character
}