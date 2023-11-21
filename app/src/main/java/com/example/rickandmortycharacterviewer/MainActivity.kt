package com.example.rickandmortycharacterviewer

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.rickandmortycharacterviewer.api.CharacterImageService
import com.example.rickandmortycharacterviewer.api.RetrofitProvider
import com.example.rickandmortycharacterviewer.databinding.ActivityMainBinding
import com.example.rickandmortycharacterviewer.model.CharacterDto
import com.example.rickandmortycharacterviewer.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    // TODO: эти поля должны быть во ViewModel, им нечего делать в Activity, она и так бедняга перегружена работой
    //  не будем добавляеть ей ещё больше ответственности и зависимостей
    private val retrofitProvider = RetrofitProvider()
    private val characterRepository = CharacterRepository(
        retrofitProvider.provideCharacterService(),
        CharacterImageService()
    )

    // TODO: не забудьте добавлять buildFeatures { viewBinding = true } в build.gradle, если используете view binding!
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupButtons()
    }

    private fun setupButtons() {
        binding.rollCharacterBtn.setOnClickListener {
            loadCharacterData()
        }
    }

    // TODO: Пожалуйста, когда вы пройдете MVVM архитектуру, вынесите этот метод в ViewModel!
    private fun loadCharacterData() {
        // TODO: Во вьюмодели лучше использовать Dispatchers.IO, нам не нужны данные мейн потока
        CoroutineScope(Dispatchers.Main).launch {
            val characterId = generateRandomCharacterId()
            val characterDto = characterRepository.getCharacterById(characterId)
            // TODO: Когда этот код будет во вьюмодели, у нас уже не будет доступа к view binding
            //  и придётся использовать Observer, имейте это ввиду, пожалуйста
            val characterImage = characterRepository.getCharacterImage(characterDto.imageUrl, applicationContext)
            setCharacterDataViews(characterDto, characterImage)
        }
    }

    private fun generateRandomCharacterId(): Int {
        return Random.nextInt(1, 826)
    }

    private fun setCharacterDataViews(characterDto: CharacterDto, characterImage: Bitmap) {
        binding.characterIdTv.text = characterDto.id.toString()
        binding.characterNameTv.text = characterDto.name
        binding.characterImageIv.setImageBitmap(characterImage)
    }
}