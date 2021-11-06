package com.example.cryptochallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cryptochallenge.databinding.ActivityMainBinding

/**
 * Main activity where place fragments
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}