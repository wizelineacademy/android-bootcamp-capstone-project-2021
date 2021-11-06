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

/**
 * Fragment to show crypto detail information
 */
class CryptoDetailFragment : Fragment() {

    /**
     * Property that represent fragment' view
     */
    private var binding: FragmentCryptoDetailBinding? = null

    /**
     * Property that represent viewModel
     */
    private val viewModel by viewModels<CryptoDetailViewModel>()

    /**
     * Property that represent section adapter
     */
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

    /**
     * Set up view elements
     */
    private fun setUpView() {
        binding?.rvSectionList?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = sectionsAdapter
        }
    }

    /**
     * Set [viewModel] listeners
     */
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

    /**
     * Show an error message and back to previous fragment
     */
    private fun sendErrorAndExit() {
        binding?.root?.let {
            Snackbar.make(it, getString(R.string.error_message), Snackbar.LENGTH_SHORT)
                .show()
        }
        findNavController().popBackStack()
    }

    /**
     * Show or hide the loader
     *
     * @param show Indicator that determines if the loader should be shown or hidden
     */
    private fun showLoader(show: Boolean) {
        binding?.iLoader?.root?.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun onDestroy() {
        viewModel.clean()
        super.onDestroy()
    }
}