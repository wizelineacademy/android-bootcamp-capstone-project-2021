package com.alexbar10.cryptotrack.ui.cryptoDetails

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
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexbar10.cryptotrack.MainActivity
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrencyDetailsBinding
import com.alexbar10.cryptotrack.networking.NetworkStatusChecker
import com.alexbar10.cryptotrack.utils.PriceType
import com.alexbar10.cryptotrack.utils.currencyFormat
import com.alexbar10.cryptotrack.utils.getImageResource
import com.alexbar10.cryptotrack.utils.getMarket
import com.alexbar10.cryptotrack.utils.getStringResource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptocurrencyDetailsFragment : Fragment() {
    private var _binding: FragmentCryptocurrencyDetailsBinding? = null
    private val binding: FragmentCryptocurrencyDetailsBinding
        get() = _binding!!
    private val args by navArgs<CryptocurrencyDetailsFragmentArgs>()
    private val viewModel: CryptocurrencyDetailsViewModel by viewModels()
    private lateinit var ordersAdapter: OrderAdapter
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(requireActivity().getSystemService(ConnectivityManager::class.java))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentCryptocurrencyDetailsBinding
            .inflate(inflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val cryptocurrencyToShow = args.cryptocurrencyToShow

        ordersAdapter = OrderAdapter()
        binding.ordersRecyclerView.run {
            adapter = ordersAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        cryptocurrencyToShow?.let {
            (requireActivity() as MainActivity).supportActionBar?.title = getString(it.getStringResource())
            binding.lastCostValueTxt.text = it.currencyFormat() + " " + it.getMarket().uppercase()
            binding.currencyHighTxt.text = it.currencyFormat(forPrice = PriceType.HIGH) + " " + it.getMarket().uppercase()
            binding.currencyLowTxt.text = it.currencyFormat(forPrice = PriceType.LOW) + " " + it.getMarket().uppercase()
            binding.currencyLogoImage.setImageResource(it.getImageResource())
            binding.buyRadioButton.setOnCheckedChangeListener { _, _ ->
                viewModel.cryptocurrencyOrderLiveData.value?.payload?.asks?.let { data ->
                    ordersAdapter.setData(data)
                }
            }
            binding.sellRadioButton.setOnCheckedChangeListener { _, _ ->
                viewModel.cryptocurrencyOrderLiveData.value?.payload?.bids?.let { data ->
                    ordersAdapter.setData(data)
                }
            }

            // Check internet connection
            networkStatusChecker.performIfConnectedToInternet(
                { viewModel.getLocalOrders(it) },
                { viewModel.getOrderRxFor(it, requireContext()) }
            )
        }

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
        viewModel.cryptocurrencyOrderLiveData.observe(
            viewLifecycleOwner,
            Observer {
                ordersAdapter.setData(if (binding.buyRadioButton.isChecked) it.payload.bids else it.payload.asks)
            }
        )
    }
}
