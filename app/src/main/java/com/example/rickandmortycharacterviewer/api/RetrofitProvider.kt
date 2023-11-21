package com.example.rickandmortycharacterviewer.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    // TODO: не забывайте ставить "/" в конце урла!
    private val rickAndMortyUrl = "https://rickandmortyapi.com/api/"

    private val retrofit = Retrofit
        .Builder()
        .baseUrl(rickAndMortyUrl)
        // TODO: без этого GsonConverterFactory наш ретрофит превратится в тыкву, json'ы преобразовывать в объекты будет проблематично
        //  (можно также использовать Jackson, он новее, но нужно больше потратить времени на его конфигурацию в коде)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideCharacterService(): CharacterDataService {
        return retrofit.create(CharacterDataService::class.java) // TODO: CharacterDataService.class было бы в джаве, юзаем рефлексию
    }
}