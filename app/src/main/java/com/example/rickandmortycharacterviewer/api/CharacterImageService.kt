package com.example.rickandmortycharacterviewer.api

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import coil.imageLoader
import coil.request.ImageRequest

class CharacterImageService {

    // TODO: передавать контекст - плохая идея, однако чуть менее плохая, если мы передаем ApplicationContext
    //  лучше всего каким-то образом обойти это требование Coil
    suspend fun loadImage(imageUrl: String, context: Context): Bitmap {
        val request = ImageRequest
            .Builder(context)
            .data(imageUrl)
            .build()

        val image = context.imageLoader.execute(request).drawable
        return (image as BitmapDrawable).bitmap
    }
}