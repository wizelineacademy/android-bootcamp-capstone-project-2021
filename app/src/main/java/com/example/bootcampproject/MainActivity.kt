package com.example.bootcampproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import com.example.bootcampproject.databinding.ActivityMainBinding
import com.example.bootcampproject.util.UpdateRoomData
import com.example.bootcampproject.util.isOnline
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity  : AppCompatActivity() {
    @Inject
    lateinit var updateRoomData: UpdateRoomData
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        if(isOnline(this))
            updateRoomData.startObservables()

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

}