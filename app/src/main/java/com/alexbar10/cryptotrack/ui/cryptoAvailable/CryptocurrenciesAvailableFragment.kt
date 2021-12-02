package com.alexbar10.cryptotrack.ui.cryptoAvailable

import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.alexbar10.cryptotrack.MainActivity
import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrenciesAvailableBinding
import com.alexbar10.cryptotrack.domain.Cryptocurrency
import com.alexbar10.cryptotrack.networking.NetworkStatusChecker
import com.alexbar10.cryptotrack.utils.market_ars
import com.alexbar10.cryptotrack.utils.market_brl
import com.alexbar10.cryptotrack.utils.market_btc
import com.alexbar10.cryptotrack.utils.market_dai
import com.alexbar10.cryptotrack.utils.market_mxn
import com.alexbar10.cryptotrack.utils.market_usd
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptocurrenciesAvailableFragment : Fragment() {

    private var _binding: FragmentCryptocurrenciesAvailableBinding? = null
    private val binding: FragmentCryptocurrenciesAvailableBinding
        get() = _binding!!
    private lateinit var navController: NavController
    private val viewModel: CryptocurrenciesAvailableViewModel by viewModels()
    private lateinit var cryptocurrenciesAdapter: CryptocurrenciesAdapter
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(requireActivity().getSystemService(ConnectivityManager::class.java))
    }

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
        cryptocurrenciesAdapter = CryptocurrenciesAdapter { cryptocurrencySelected ->
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
        binding.marketMxnCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_mxn, isChecked) }
        binding.marketArgCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_ars, isChecked) }
        binding.marketBitcoinCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_btc, isChecked) }
        binding.marketBrlCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_brl, isChecked) }
        binding.marketDaiCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_dai, isChecked) }
        binding.marketUsdCheckbox.setOnCheckedChangeListener { _, isChecked -> viewModel.showCryptocurrenciesFor(market_usd, isChecked) }

        // Check internet connection
        networkStatusChecker.performIfConnectedToInternet(
            {
                viewModel.getLocalCryptos()
                Toast.makeText(requireContext(), getString(R.string.not_internet_available), Toast.LENGTH_LONG).show()
            },
            { viewModel.getCryptocurrenciesAvailable() }
        )
        setupObservables()
    }

    private fun setupObservables() {
        viewModel.loadingLiveData.observe(
            viewLifecycleOwner,
            Observer { value: Boolean ->
                binding.loaderAnimationView.isVisible = value
            }
        )
        viewModel.errorLiveData.observe(
            viewLifecycleOwner,
            Observer {
                Toast.makeText(requireContext(), it?.message, Toast.LENGTH_LONG).show()
            }
        )
        viewModel.cryptoAvailableDetailsLiveData.observe(
            viewLifecycleOwner,
            Observer { cryptocurrencies: List<Cryptocurrency> ->
                cryptocurrenciesAdapter.setData(cryptocurrencies)
                binding.cryptocurrenciesRecyclerView.visibility = View.VISIBLE
            }
        )
        viewModel.cryptoFilterLiveData.observe(
            viewLifecycleOwner,
            Observer { value: List<Cryptocurrency> ->
                cryptocurrenciesAdapter.setData(value)
                binding.cryptocurrenciesRecyclerView.visibility = View.VISIBLE
            }
        )
    }
}
