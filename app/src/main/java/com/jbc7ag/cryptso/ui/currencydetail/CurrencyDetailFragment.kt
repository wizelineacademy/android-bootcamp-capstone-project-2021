package com.jbc7ag.cryptso.ui.currencydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jbc7ag.cryptso.databinding.FragmentCurrencyDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment: Fragment() {

    private lateinit var binding: FragmentCurrencyDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyDetailsBinding.inflate(layoutInflater, container, false)
            .apply { binding = this }
            .root
    }
}