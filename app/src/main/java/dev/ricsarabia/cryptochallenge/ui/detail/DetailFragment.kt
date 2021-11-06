package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class DetailFragment : Fragment() {
    companion object { fun newInstance() = DetailFragment() }

    private lateinit var binding: DetailFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.getBookPrices()
        viewModel.getBookOrders()

        viewModel.selectedBookPrices.observe(viewLifecycleOwner, {
            binding.lastPriceTextView.text = "last " + it.last
            binding.higherPriceTextView.text = "higher " + it.high
            binding.lowerPriceTextView.text = "lower " + it.low
        })
        viewModel.selectedBookOrders.observe(viewLifecycleOwner, {
            Log.wtf("selectedBookOrders", it.toString())
        })
    }
}