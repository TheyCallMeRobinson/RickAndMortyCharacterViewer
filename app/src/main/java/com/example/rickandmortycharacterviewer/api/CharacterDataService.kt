package com.example.rickandmortycharacterviewer.api

import com.example.rickandmortycharacterviewer.model.CharacterDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CharacterDataService {

    @GET("character/{id}")
    suspend fun getCharacterById(@Path("id") characterId: Int): CharacterDto
}