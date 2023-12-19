package com.example.rickandmortycharacterviewer.fragment

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.rickandmortycharacterviewer.R
import com.example.rickandmortycharacterviewer.databinding.FragmentCharacterViewerBinding
import com.example.rickandmortycharacterviewer.databinding.FragmentWelcomeScreenBinding

class WelcomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentWelcomeScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeScreenBinding.inflate(inflater)

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
        binding.toCharactersBtn.setOnClickListener(
            Navigation.createNavigateOnClickListener(R.id.action_welcomeScreenFragment_to_characterViewerFragment)
        )
    }

}