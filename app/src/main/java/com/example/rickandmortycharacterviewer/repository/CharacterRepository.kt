package com.example.rickandmortycharacterviewer.repository

import android.content.Context
import android.graphics.Bitmap
import com.example.rickandmortycharacterviewer.api.CharacterImageService
import com.example.rickandmortycharacterviewer.api.CharacterDataService
import com.example.rickandmortycharacterviewer.model.CharacterDto

class CharacterRepository(
    private val characterDataService: CharacterDataService,
    private val characterImageService: CharacterImageService
) {
    // TODO: чтобы сделать метод корутиной, не забываем слово suspend
    suspend fun getCharacterById(id: Int): CharacterDto {
        return characterDataService.getCharacterById(id)
    }

    suspend fun getCharacterImage(url: String, context: Context): Bitmap {
        return characterImageService.loadImage(url, context)
    }
}