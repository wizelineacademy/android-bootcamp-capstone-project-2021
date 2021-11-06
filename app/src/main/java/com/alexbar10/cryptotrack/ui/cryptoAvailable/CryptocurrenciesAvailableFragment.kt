package com.alexbar10.cryptotrack.ui.cryptoAvailable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrenciesAvailableBinding
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptocurrenciesAvailableFragment : Fragment() {

    private var _binding: FragmentCryptocurrenciesAvailableBinding? = null
    private val binding: FragmentCryptocurrenciesAvailableBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: CryptocurrenciesAvailableViewModel by viewModels()
    private lateinit var cryptocurrenciesAdapter: CryptocurrenciesAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCryptocurrenciesAvailableBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        cryptocurrenciesAdapter = CryptocurrenciesAdapter {  cryptocurrencySelected ->
            println(cryptocurrencySelected)
        }

        binding.cryptocurrenciesRecyclerView.run {
            adapter = cryptocurrenciesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getCryptocurrenciesAvailable()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { value: Boolean ->
            println("Error $value")
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            println("Error $it")
        })
        viewModel.cryptoAvailableDetailsLiveData.observe(viewLifecycleOwner, Observer { cryptocurrencies: List<Cryptocurrency> ->
            cryptocurrenciesAdapter.setData(cryptocurrencies)
            binding.cryptocurrenciesRecyclerView.visibility = View.VISIBLE
        })
    }

}