package com.jbc7ag.cryptso.ui.currencieslist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.jbc7ag.cryptso.data.model.Book
import com.jbc7ag.cryptso.databinding.FragmentCurrenciesBinding
import com.jbc7ag.cryptso.util.getCurrencyCodeFilter
import com.jbc7ag.cryptso.util.getFilterList
import dagger.hilt.android.AndroidEntryPoint
import com.jbc7ag.cryptso.util.FILTER


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
        navController = findNavController()
    }

    private fun initObservers() {
        viewModel.apply {
            error.observe(viewLifecycleOwner, {
                it?.let {
                    showEmptyScreen(true)
                }
            })

            availableBooks.observe(viewLifecycleOwner, {
                if (it.isNotEmpty()) {
                    showCurrencyList(it)
                    showFilters(it)
                }
                showEmptyScreen(it.isEmpty())
            })

            loading.observe(viewLifecycleOwner, {
                binding?.progressLoader?.visibility = if(it) View.VISIBLE else View.GONE
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
        val dataFilter = if(!getFilter().isNullOrEmpty()) getFilter() else data[0].book.getCurrencyCodeFilter()
        currenciesAdapter.submitList(data.filter { it.book.getCurrencyCodeFilter() == dataFilter})
    }

    private fun showFilters(data: List<Book>) {

        currenciesFilterAdapter = CurrenciesFilterAdapter { book ->
            saveFilter(book)
            val dataFilter = data.filter { it.book.getCurrencyCodeFilter() == book }
            currenciesAdapter.submitList(dataFilter)
            currenciesFilterAdapter.currentList.map {
                it.selected = it.name == book
            }
            currenciesFilterAdapter.notifyDataSetChanged()
        }

        binding?.filterList?.adapter = currenciesFilterAdapter
        val dataFilter = if(!getFilter().isNullOrEmpty()) getFilter() else data[0].book.getCurrencyCodeFilter()
        currenciesFilterAdapter.submitList(data.getFilterList(dataFilter))
    }

    private fun showEmptyScreen(visible: Boolean){
        binding?.noItemsView?.root?.visibility = if(visible) View.VISIBLE else View.GONE
    }

    private fun saveFilter(filter: String){
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
        with (sharedPref.edit()) {
            putString(FILTER, filter)
            apply()
        }
    }

    private fun getFilter(): String? {
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE) ?: return ""
        return sharedPref.getString(FILTER, "")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
