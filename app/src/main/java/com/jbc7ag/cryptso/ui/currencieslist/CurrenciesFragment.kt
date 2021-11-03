package com.jbc7ag.cryptso.ui.currencieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jbc7ag.cryptso.databinding.FragmentCurrenciesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment: Fragment() {

    private lateinit var binding: FragmentCurrenciesBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentCurrenciesBinding.inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.texty.setOnClickListener {
            CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailFragment(0).let{
                navController.navigate(it)
            }
        }

        navController = findNavController()
    }
}