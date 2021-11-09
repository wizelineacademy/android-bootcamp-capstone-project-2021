package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: DetailFragmentBinding // TODO: correct this horrible screen design
    private val viewModel by lazy { ViewModelProvider(requireActivity()).get(MainViewModel::class.java) }
    private val asksAdapter = OrdersAdapter()
    private val bidsAdapter = OrdersAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Init components
        binding.asksLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.asksLinearLayout.adapter = asksAdapter
        binding.bidsLinearLayout.layoutManager = LinearLayoutManager(context)
        binding.bidsLinearLayout.adapter = bidsAdapter

        // Init observers
        viewModel.selectedBookPrices.observe(viewLifecycleOwner, {
            binding.lastPriceTextView.text = "last " + it.last
            binding.higherPriceTextView.text = "higher " + it.high
            binding.lowerPriceTextView.text = "lower " + it.low
        })
        viewModel.selectedBookOrders.observe(viewLifecycleOwner, {
            asksAdapter.orders = it.asks
            bidsAdapter.orders = it.bids
        })

        // Retrieve book details
        viewModel.getBookPrices()
        viewModel.getBookOrders()
        // TODO: Show a "loading" animation while retrieving data
    }
}