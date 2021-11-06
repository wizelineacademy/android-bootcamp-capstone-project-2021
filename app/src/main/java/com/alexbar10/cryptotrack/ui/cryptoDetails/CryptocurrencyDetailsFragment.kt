package com.alexbar10.cryptotrack.ui.cryptoDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.alexbar10.cryptotrack.MainActivity
import com.alexbar10.cryptotrack.R
import com.alexbar10.cryptotrack.databinding.FragmentCryptocurrencyDetailsBinding
import com.alexbar10.cryptotrack.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CryptocurrencyDetailsFragment : Fragment() {
    private var _binding: FragmentCryptocurrencyDetailsBinding? = null
    private val binding: FragmentCryptocurrencyDetailsBinding
        get() = _binding!!
    private val args by navArgs<CryptocurrencyDetailsFragmentArgs>()
    private val viewModel: CryptocurrencyDetailsViewModel by viewModels()
    private lateinit var ordersAdapter: OrderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
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
            (requireActivity() as MainActivity).supportActionBar?.title = getString(getNameFor(it))
            binding.lastCostValueTxt.text = currencyFormat(it) + " " + getMarketFor(it).uppercase()
            binding.currencyHighTxt.text = currencyFormat(it, forPrice = PriceType.HIGH) + " " + getMarketFor(it).uppercase()
            binding.currencyLowTxt.text = currencyFormat(it, forPrice = PriceType.LOW) + " " + getMarketFor(it).uppercase()
            binding.currencyLogoImage.setImageResource(getImageResourceFor(it))
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

            viewModel.getOrderFor(it)
        }

        setupObservables()
    }

    private fun setupObservables() {
        viewModel.cryptocurrencyOrderLiveData.observe(viewLifecycleOwner, Observer {
            ordersAdapter.setData(if (binding.buyRadioButton.isChecked) it.payload.bids else it.payload.asks)
        })
    }
}