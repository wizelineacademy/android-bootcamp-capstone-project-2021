package com.example.capstoneproject.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.capstoneproject.databinding.MainFragmentBinding

// view holders' height should take 1/7 of the screen
private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 7.0

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding : MainFragmentBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currencyAdapter = CurrencyAdapter { bookName ->
            Log.e("BITSO", "Currency clicked $bookName")
        }

        binding.currencyList.run {
            adapter = currencyAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
        }

        mainViewModel.loadCurrencies()

        mainViewModel.currencyList.observe(viewLifecycleOwner) {
            Log.e("BITSO", "Currencies loaded")
            currencyAdapter.submitList(mainViewModel.currencyList.value)
        }
    }
}