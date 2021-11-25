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
import com.jbc7ag.cryptso.util.getCurrencyCodeFilter
import com.jbc7ag.cryptso.util.getFilterList
import com.jbc7ag.cryptso.util.isInternetAvailable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrenciesFragment : Fragment() {

    private var _binding: FragmentCurrenciesBinding? = null
    private val binding get() = _binding

    private lateinit var navController: NavController
    private lateinit var currenciesAdapter: CurrenciesAdapter
    private lateinit var currenciesFilterAdapter: CurrenciesFilterAdapter
    private val viewModel: CurrenciesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentCurrenciesBinding.inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()

        if (context?.let { isInternetAvailable(it) } == true)
            viewModel.getCoinList()

        navController = findNavController()
    }

    private fun initObservers() {
        viewModel.apply {
            error.observe(viewLifecycleOwner, {
                it?.let {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                }
            })
            availableBooks.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    binding?.noItemsView?.root?.visibility = View.GONE
                    showCurrencyList(it)
                    showFilters(it)
                } else {
                    binding?.noItemsView?.root?.visibility = View.VISIBLE
                }
            })
            loading.observe(viewLifecycleOwner, {
                if (!it) {
                    viewModel.getBooks()
                }
            })
        }
    }

    private fun showCurrencyList(data: List<Book>) {
        currenciesAdapter = CurrenciesAdapter { book ->
            CurrenciesFragmentDirections.actionCurrenciesFragmentToCurrencyDetailFragment(book)
                .let {
                    navController.navigate(it)
                }
        }
        binding?.currenciesList?.adapter = currenciesAdapter
        currenciesAdapter.submitList(data.filter { it.book.getCurrencyCodeFilter() == data[0].book.getCurrencyCodeFilter() })
    }

    private fun showFilters(data: List<Book>) {

        currenciesFilterAdapter = CurrenciesFilterAdapter { book ->
            val dataFilter = data.filter { it.book.getCurrencyCodeFilter() == book }
            currenciesAdapter.submitList(dataFilter)
            currenciesFilterAdapter.currentList.map {
                it.selected = it.name == book
            }
            currenciesFilterAdapter.notifyDataSetChanged()
        }

        binding?.filterList?.adapter = currenciesFilterAdapter
        currenciesFilterAdapter.submitList(data.getFilterList(data[0].book.getCurrencyCodeFilter()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
