package com.example.rickandmortycharacterviewer.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    val id: Int,
    val name: String,
    val species: String,
    val status: String,
    val gender: String,
    val location: LocationDto,
    @SerializedName("image") val imageUrl: String
)