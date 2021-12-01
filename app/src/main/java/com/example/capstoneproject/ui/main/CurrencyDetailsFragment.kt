package com.example.capstoneproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.capstoneproject.databinding.FragmentCurrencyDetailsBinding

class CurrencyDetailsFragment : Fragment() {
    private var bookName: String? = null

    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding!!

    private val mainViewModel: MainViewModel by viewModels()

    private val args: CurrencyDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bookName = args.bookName
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCurrencyDetailsBinding.inflate(inflater, container, false)
        binding.bookNameText.text = bookName

        bookName?.let { mainViewModel.getBookInfo(it) }
        val imageUrl: String = "https://cryptoicon-api.vercel.app/api/icon/" +
                bookName?.substringBefore("_", "")
        Glide.with(binding.currencyImageView.context).load(imageUrl).fitCenter()
            .into(binding.currencyImageView)

        mainViewModel.tickerInfo.observe(viewLifecycleOwner) { ticker ->
            Log.e("BITSO", "Max price" + ticker.highPrice)
            binding.highestTextView.text = ticker.highPrice
            binding.lowestTextView.text = ticker.lowPrice
            binding.lastTextView.text = ticker.lastPrice
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}