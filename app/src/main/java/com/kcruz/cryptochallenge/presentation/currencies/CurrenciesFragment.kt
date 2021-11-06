package com.kcruz.cryptochallenge.presentation.currencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kcruz.cryptochallenge.databinding.FragmentCurrenciesBinding
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.presentation.currencies.adapter.CurrenciesAdapter

//TODO: Add documentation

class CurrenciesFragment : Fragment() {

    private val viewModel by viewModels<CurrenciesViewModel> ()
    private var binding: FragmentCurrenciesBinding? = null
    private lateinit var navController: NavController
    private var currenciesAdapter: CurrenciesAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrenciesBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        setViewModelListeners()
    }

    private fun setupAdapter(list: List<ExchangeOrderBook>) {
        currenciesAdapter = CurrenciesAdapter()
        currenciesAdapter?.submitList(list)

        currenciesAdapter?.onItemSelected = {
            val action = CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetail()
            view?.findNavController()?.navigate(action)
        }

        binding?.rvCurrencies?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = currenciesAdapter
        }
    }

    private fun setViewModelListeners() {
        viewModel.availableBooks.observe(viewLifecycleOwner) { list ->
            setupAdapter(list)
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when(event) {
                is SendMessage -> showMessage(event.message)
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }
}