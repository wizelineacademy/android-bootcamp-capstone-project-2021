package com.example.cryptochallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}