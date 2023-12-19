package com.example.rickandmortycharacterviewer.fragment

import android.graphics.Bitmap
import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.rickandmortycharacterviewer.R
import com.example.rickandmortycharacterviewer.api.CharacterImageService
import com.example.rickandmortycharacterviewer.api.RetrofitProvider
import com.example.rickandmortycharacterviewer.databinding.FragmentCharacterViewerBinding
import com.example.rickandmortycharacterviewer.model.CharacterDto
import com.example.rickandmortycharacterviewer.repository.CharacterRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class CharacterViewerFragment : Fragment() {

    // TODO: эти поля должны быть во ViewModel, им нечего делать в Activity, она и так бедняга перегружена работой
    //  не будем добавляеть ей ещё больше ответственности и зависимостей
    private val retrofitProvider = RetrofitProvider()
    private val characterRepository = CharacterRepository(
        retrofitProvider.provideCharacterService(),
        CharacterImageService()
    )

    private lateinit var binding: FragmentCharacterViewerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterViewerBinding.inflate(inflater)

        setupTransitions()
        setupButtons()

        return binding.root
    }

    private fun setupTransitions() {
        val transitionInflater = TransitionInflater.from(requireContext())
        enterTransition = transitionInflater.inflateTransition(R.transition.slide_left)
        exitTransition = transitionInflater.inflateTransition(R.transition.slide_right)
    }

    private fun setupButtons() {
        binding.rollCharacterBtn.setOnClickListener {
            loadCharacterData()
        }

        binding.backBtn.setOnClickListener {
            it.findNavController().navigateUp()
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
            val characterImage = characterRepository.getCharacterImage(characterDto.imageUrl, requireContext())
            setCharacterDataViews(characterDto, characterImage)
        }
    }

    private fun generateRandomCharacterId(): Int {
        return Random.nextInt(1, 826)
    }

    private fun setCharacterDataViews(characterDto: CharacterDto, characterImage: Bitmap) {
        binding.characterNameTv.text = characterDto.name
        binding.characterImageIv.setImageBitmap(characterImage)
        binding.includeDetailedInfo.includeCharacterNameTv.text = characterDto.name
        binding.includeDetailedInfo.includeCharacterStatusTv.text = characterDto.status
        binding.includeDetailedInfo.includeCharacterLocationTv.text = characterDto.location.name
        binding.includeDetailedInfo.includeCharacterSpeciesTv.text = characterDto.species
    }
}