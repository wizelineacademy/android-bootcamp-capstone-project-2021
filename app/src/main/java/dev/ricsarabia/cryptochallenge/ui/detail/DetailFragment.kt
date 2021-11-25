package dev.ricsarabia.cryptochallenge.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.BookPrices
import dev.ricsarabia.cryptochallenge.utils.asDecimalPrice

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val asksAdapter = OrdersAdapter()
    private val bidsAdapter = OrdersAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { viewModel.setSelectedBook(it.getString("BOOK") ?: "") }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initObservers()
        viewModel.updateBookPrices()
        viewModel.updateBookOrders()
    }

    private fun initViews() = binding.run {
        asksLinearLayout.layoutManager = LinearLayoutManager(context)
        asksLinearLayout.adapter = asksAdapter
        asksSwipeRefresh.setOnRefreshListener { viewModel.run { updateBookOrders(); updateBookPrices() } }
        bidsLinearLayout.layoutManager = LinearLayoutManager(context)
        bidsLinearLayout.adapter = bidsAdapter
        bidsSwipeRefresh.setOnRefreshListener { viewModel.run { updateBookOrders(); updateBookPrices() } }
    }

    private fun initObservers() = viewModel.run {
        selectedBookPrices.observe(viewLifecycleOwner, { setPrices(it) })
        selectedBookAsks.observe(viewLifecycleOwner, { asksAdapter.submitList(it) })
        selectedBookBids.observe(viewLifecycleOwner, { bidsAdapter.submitList(it) })
        loading.observe(viewLifecycleOwner, {
            binding.asksSwipeRefresh.isRefreshing = it
            binding.bidsSwipeRefresh.isRefreshing = it
        })
    }

    private fun setPrices(prices: BookPrices?) = binding.run {
        val mPrices = prices ?: BookPrices("", "", "", "")
        majorTextView.text = mPrices.book.substringBefore("_").uppercase()
        minorTextView.text = mPrices.book.substringAfter("_").uppercase()
        lastPriceTextView.text = mPrices.last.asDecimalPrice()
        higherPriceTextView.text = mPrices.high.asDecimalPrice()
        lowerPriceTextView.text = mPrices.low.asDecimalPrice()
    }
}