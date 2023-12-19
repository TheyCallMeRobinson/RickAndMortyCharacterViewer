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

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}