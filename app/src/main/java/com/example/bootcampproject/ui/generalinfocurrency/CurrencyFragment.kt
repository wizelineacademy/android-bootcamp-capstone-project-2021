package com.example.bootcampproject.ui.generalinfocurrency

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.mockLogos
import com.example.bootcampproject.databinding.FragmentCurrencyBinding
import dagger.hilt.android.AndroidEntryPoint

private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 5.0

@AndroidEntryPoint
class CurrencyFragment : Fragment() {
    private var _binding: FragmentCurrencyBinding? = null
    private val binding: FragmentCurrencyBinding
        get() = _binding!!

    private val viewModel: CurrencyViewModel by viewModels()

    private lateinit var navController: NavController

    private lateinit var currencyAdapter: CurrencyAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        currencyAdapter=CurrencyAdapter {  }
        /* currencyAdapter = CurrencyAdapter { pokemonId ->
            FeaturedPokemonFragmentDirections
                .toPokemonDetailFragment(pokemonId)
                .let { navController.navigate(it) }
        }
        navController = findNavController()*/
        viewModel.getActualCurrencies()
        viewModel.currencies.observe(viewLifecycleOwner,{currencies->
            binding.currencyList.run {
                adapter = currencyAdapter
                layoutManager = object : LinearLayoutManager(requireContext()) {
                    override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                        lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                        return true
                    }
                }
            }
            /*  currencyAdapter=CurrencyAdapter {  }
              binding.currencyList.layoutManager = LinearLayoutManager(this.context)
              binding.currencyList.adapter=currencyAdapter*/
            currencyAdapter.submitList(currencies)
        })


    }
}