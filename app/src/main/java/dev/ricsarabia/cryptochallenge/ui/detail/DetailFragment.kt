package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.databinding.MainFragmentBinding
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class DetailFragment : Fragment() {
    companion object { fun newInstance() = DetailFragment() }

    private lateinit var binding: DetailFragmentBinding // TODO: correct this horrible screen design
    private lateinit var viewModel: MainViewModel
    private val asksAdapter = OrdersAdapter()
    private val bidsAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.asksLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.asksLinearLayout.adapter = asksAdapter

        binding.bidsLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.bidsLinearLayout.adapter = bidsAdapter

        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.getBookPrices()
        viewModel.getBookOrders()
        // TODO: Show a "loading" animation while retrieving data
        
        viewModel.selectedBookPrices.observe(viewLifecycleOwner, {
            binding.lastPriceTextView.text = "last " + it.last
            binding.higherPriceTextView.text = "higher " + it.high
            binding.lowerPriceTextView.text = "lower " + it.low
        })
        viewModel.selectedBookOrders.observe(viewLifecycleOwner, {
            asksAdapter.orders = it.asks
            bidsAdapter.orders = it.bids
        })
    }
}