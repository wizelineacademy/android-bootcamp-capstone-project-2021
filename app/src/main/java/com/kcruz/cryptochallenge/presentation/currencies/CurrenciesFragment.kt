package com.kcruz.cryptochallenge.presentation.currencies

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kcruz.cryptochallenge.databinding.FragmentCurrenciesBinding
import com.kcruz.cryptochallenge.domain.ExchangeOrderBook
import com.kcruz.cryptochallenge.presentation.currencies.adapter.CurrenciesAdapter

class CurrenciesFragment : Fragment() {

    private val viewModel by viewModels<CurrenciesViewModel> ()
    private var binding: FragmentCurrenciesBinding? = null
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
        setViewModelListeners()
    }

    private fun setupAdapter(list: List<ExchangeOrderBook>) {
        currenciesAdapter = CurrenciesAdapter()
        currenciesAdapter?.submitList(list)

        currenciesAdapter?.onItemSelected = {

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

    }

    companion object {

        @JvmStatic
        fun newInstance() =
            CurrenciesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}