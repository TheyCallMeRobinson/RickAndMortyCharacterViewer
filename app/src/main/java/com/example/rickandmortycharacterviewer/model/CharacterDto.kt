package com.example.rickandmortycharacterviewer.model

import com.google.gson.annotations.SerializedName

data class CharacterDto(
    val id: Int,
    val name: String,
    @SerializedName("image") val imageUrl: String
)