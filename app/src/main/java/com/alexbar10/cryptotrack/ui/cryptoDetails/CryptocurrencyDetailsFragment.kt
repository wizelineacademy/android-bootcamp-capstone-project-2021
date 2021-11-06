package com.alexbar10.cryptotrack.ui.cryptoDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrencyDetailsBinding

class CryptocurrencyDetailsFragment : Fragment() {
    private var _binding: FragmentCryptocurrencyDetailsBinding? = null
    private val binding: FragmentCryptocurrencyDetailsBinding
        get() = _binding!!
    private val args by navArgs<CryptocurrencyDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCryptocurrencyDetailsBinding
            .inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val currencyToShow = args.cryptocurrencyToShow
        println("CurrencyToShow ${currencyToShow.book}")

    }
}