package com.alexbar10.cryptotrack.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrenciesAvailableBinding

class CryptocurrenciesAvailableFragment : Fragment() {

    private var _binding: FragmentCryptocurrenciesAvailableBinding? = null
    private val binding: FragmentCryptocurrenciesAvailableBinding
        get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCryptocurrenciesAvailableBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

}