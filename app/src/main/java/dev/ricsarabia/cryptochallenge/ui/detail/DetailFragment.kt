package dev.ricsarabia.cryptochallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_INDEFINITE
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.databinding.DetailFragmentBinding
import dev.ricsarabia.cryptochallenge.domain.BookPrices
import dev.ricsarabia.cryptochallenge.utils.asDecimalPrice
import dev.ricsarabia.cryptochallenge.utils.asLocalDate

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private var _binding: DetailFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: DetailViewModel by viewModels()
    private val asksAdapter = OrdersAdapter()
    private val bidsAdapter = OrdersAdapter()
    private lateinit var snackbar: Snackbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { viewModel.setSelectedBook(it.getString("BOOK") ?: "") }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        snackbar.dismiss()
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
        snackbar =
            Snackbar.make(binding.detailRootView, R.string.no_data_available, LENGTH_INDEFINITE)
    }

    private fun initObservers() = viewModel.run {
        selectedBookPrices.observe(viewLifecycleOwner) { setPrices(it) }
        selectedBookAsks.observe(viewLifecycleOwner) { asksAdapter.submitList(it) }
        selectedBookBids.observe(viewLifecycleOwner) { bidsAdapter.submitList(it) }
        selectedBookLastRefresh.observe(viewLifecycleOwner) {
            it?.let { binding.refreshTimeTextView.text = it.dateTime.asLocalDate() }
        }
        loading.observe(viewLifecycleOwner) {
            binding.asksSwipeRefresh.isRefreshing = it
            binding.bidsSwipeRefresh.isRefreshing = it
        }
        dataError.observe(viewLifecycleOwner) { if (it) showErrorDialog() }
        refreshSucceeded.observe(viewLifecycleOwner) {
            when (it) {
                true -> snackbar.dismiss()
                false -> snackbar.show()
            }
        }
    }

    private fun setPrices(prices: BookPrices?) = binding.run {
        val mPrices = prices ?: BookPrices("", "", "", "")
        majorTextView.text = mPrices.book.substringBefore("_").uppercase()
        minorTextView.text = mPrices.book.substringAfter("_").uppercase()
        lastPriceTextView.text = mPrices.last.asDecimalPrice()
        higherPriceTextView.text = mPrices.high.asDecimalPrice()
        lowerPriceTextView.text = mPrices.low.asDecimalPrice()
    }

    private fun showErrorDialog() {
        val dialog: AlertDialog? = activity?.let {
            AlertDialog.Builder(it).run {
                setMessage(R.string.data_error_message)
                setPositiveButton(R.string.ok) { _, _ -> findNavController().popBackStack() }
                setCancelable(false)
                create()
            }
        }
        dialog?.show()
    }
}
