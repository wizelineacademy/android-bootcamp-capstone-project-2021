package com.example.cryptochallenge.ui.cryptodetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptochallenge.R
import com.example.cryptochallenge.databinding.FragmentCryptoDetailBinding
import com.example.cryptochallenge.ui.cryptodetail.adapter.SectionsAdapter
import com.example.cryptochallenge.ui.home.HomeFragment.Companion.CRYPTO_NAME
import com.google.android.material.snackbar.Snackbar

class CryptoDetailFragment : Fragment() {

    private var binding: FragmentCryptoDetailBinding? = null
    private val viewModel by viewModels<CryptoDetailViewModel>()
    private val sectionsAdapter = SectionsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCryptoDetailBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cryptoName = arguments?.getString(CRYPTO_NAME) ?: ""
        if (cryptoName.isNotEmpty()) {
            setUpView()
            setViewModelListener(cryptoName)
            viewModel.setItem(cryptoName)
        } else {
            sendErrorAndExit()
        }
    }

    private fun setUpView() {
        binding?.rvSectionList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sectionsAdapter
        }
    }

    private fun setViewModelListener(cryptoName: String) {
        viewModel.getTicker(cryptoName).observe(viewLifecycleOwner) {
            viewModel.setItem(it)
        }

        viewModel.getOrderBook(cryptoName).observe(viewLifecycleOwner) {
            viewModel.setItem(it)
        }

        viewModel.sections.observe(viewLifecycleOwner) {
            sectionsAdapter.submitList(it)
            sectionsAdapter.notifyDataSetChanged()
        }

        viewModel.showLoader.observe(viewLifecycleOwner) {
            showLoader(it)
        }
    }

    private fun sendErrorAndExit() {
        binding?.root?.let {
            Snackbar.make(it, getString(R.string.error_message), Snackbar.LENGTH_SHORT)
                .show()
        }
        findNavController().popBackStack()
    }

    private fun showLoader(show: Boolean) {
        binding?.iLoader?.root?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        viewModel.clean()
        super.onDestroy()
    }
}