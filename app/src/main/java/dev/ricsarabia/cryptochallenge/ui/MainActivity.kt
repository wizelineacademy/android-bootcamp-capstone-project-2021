package dev.ricsarabia.cryptochallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.ui.detail.DetailFragment
import dev.ricsarabia.cryptochallenge.ui.main.MainFragment

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        //Observers
        viewModel.selectedBook.observe(this, {
            if (it.isEmpty()) return@observe
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, DetailFragment.newInstance())
                .commitNow()
            // TODO: implement navigation correctly
        })
    }

    override fun onBackPressed() {
        //TODO: implement navigation
    }
}