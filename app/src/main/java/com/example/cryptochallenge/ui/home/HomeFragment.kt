package com.example.cryptochallenge.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentHomeBinding
import com.example.cryptochallenge.domain.availablebook.AvailableBook
import com.example.cryptochallenge.domain.availablebook.Payload
import com.example.cryptochallenge.ui.home.adapter.CryptocurrencyAdapter

class HomeFragment : Fragment() {
    private var binding: FragmentHomeBinding? = null
    private val viewModel by viewModels<HomeViewModel>()

    private val cryptoCurrencyAdapter = CryptocurrencyAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpView()
        setViewModelListener()
    }

    private fun setViewModelListener() {
        viewModel.getAvailableBooks().observe(viewLifecycleOwner) {
            if (!it.isNullOrEmpty())
                setAvailableBooks(it)
            else
                showEmtpyState(true)
            showLoader(false)
        }

        viewModel.eventTrigger.observe(viewLifecycleOwner) {
            when (it) {
                is HomeEvent.OnShowCryptoDetail -> toCryptoDetail(it.cryptoName)
            }
        }
    }

    private fun setUpView() {
        binding?.rvCryptocurrencyList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = cryptoCurrencyAdapter
        }

        cryptoCurrencyAdapter.onItemSelect = { cryptoName ->
            viewModel.showCryptoDetail(cryptoName)
        }
    }

    private fun setAvailableBooks(cryptoCurrencyList: List<Payload>) {
        showEmtpyState(false)
        cryptoCurrencyAdapter.submitList(cryptoCurrencyList)
    }

    private fun showEmtpyState(show: Boolean) {
        binding?.rvCryptocurrencyList?.visibility = if (show) View.GONE else View.VISIBLE
        binding?.tvEmptyState?.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun showLoader(show: Boolean) {
        binding?.iLoader?.root?.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun toCryptoDetail(cryptoName: String) {
        val bundle = bundleOf(CRYPTO_NAME to cryptoName)
        findNavController().navigate(R.id.toCryptoDetail, bundle)
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    companion object {
        const val CRYPTO_NAME = "cryptoName"
    }
}