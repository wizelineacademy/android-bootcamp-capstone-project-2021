package com.alexbar10.cryptotrack.ui.cryptoAvailable

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alexbar10.cryptotrack.MainActivity
import com.alexbar10.cryptotrack.R
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
            CryptocurrenciesAvailableFragmentDirections
                .actionCryptocurrenciesAvailableFragmentToCryptocurrencyDetailsFragment(cryptocurrencySelected)
                .let { navController.navigate(it) }
        }
        navController = findNavController()
        (requireActivity() as MainActivity).supportActionBar?.title = getString(R.string.app_name)

        binding.cryptocurrenciesRecyclerView.run {
            adapter = cryptocurrenciesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
        binding.marketMxnCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("mxn", isChecked) }
        binding.marketArgCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("ars", isChecked) }
        binding.marketBitcoinCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("btc", isChecked) }
        binding.marketBrlCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("brl", isChecked) }
        binding.marketDaiCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("dai", isChecked) }
        binding.marketUsdCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor("usd", isChecked) }

        viewModel.getCryptocurrenciesAvailable()
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer { value: Boolean ->
            binding.loaderAnimationView.isVisible = value
        })
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it?.message, Toast.LENGTH_LONG).show()
        })
        viewModel.cryptoAvailableDetailsLiveData.observe(viewLifecycleOwner, Observer { cryptocurrencies: List<Cryptocurrency> ->
            cryptocurrenciesAdapter.setData(cryptocurrencies)
            binding.cryptocurrenciesRecyclerView.visibility = View.VISIBLE
        })
        viewModel.cryptoFilterLiveData.observe(viewLifecycleOwner, Observer { value: List<Cryptocurrency> ->
            cryptocurrenciesAdapter.setData(value)
            binding.cryptocurrenciesRecyclerView.visibility = View.VISIBLE
        })
    }

}