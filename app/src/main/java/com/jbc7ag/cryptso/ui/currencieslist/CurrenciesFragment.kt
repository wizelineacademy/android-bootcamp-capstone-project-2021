package com.jbc7ag.cryptso.ui.currencieslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.databinding.FragmentCurrenciesBinding
import com.jbc7ag.cryptso.util.filterCurrencies
import com.jbc7ag.cryptso.util.setNameCurrencies
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment: Fragment() {

    private lateinit var binding: FragmentCurrenciesBinding
    private lateinit var navController: NavController
    private lateinit var currenciesAdapter: CurrenciesAdapter
    private val viewModel: CurrenciesViewModel by viewModels()

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
        viewModel.getAvailableBooks()
        observers()
        navController = findNavController()
    }

    private fun observers(){
        viewModel.apply {
            error.observe(viewLifecycleOwner, {
                Toast.makeText(activity, it, Toast.LENGTH_LONG).show()
            })
            availableBooks.observe(viewLifecycleOwner,{
                showCurrencyList(it)
            })
        }
    }

    private fun showCurrencyList(data: List<Book>){
        currenciesAdapter = CurrenciesAdapter { book ->
            CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailFragment(book).let{
                navController.navigate(it)
            }
        }

        binding.currenciesList.run {
            adapter = currenciesAdapter
        }
        data.setNameCurrencies()
        currenciesAdapter.submitList(data.filterCurrencies())
    }
}