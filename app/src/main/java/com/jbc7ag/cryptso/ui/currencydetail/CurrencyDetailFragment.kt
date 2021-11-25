package com.jbc7ag.cryptso.ui.currencydetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.databinding.FragmentCurrencyDetailsBinding
import com.jbc7ag.cryptso.util.getCurrencyCode
import com.jbc7ag.cryptso.util.getmarketFormat
import com.jbc7ag.cryptso.util.formatCurrency
import com.jbc7ag.cryptso.util.IMAGES_URL
import com.jbc7ag.cryptso.R
import com.jbc7ag.cryptso.util.TYPES
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding
    private val viewModel: CurrencyDetailViewModel by viewModels()
    private lateinit var bidsAdapter: BidsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyDetailsBinding.inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = CurrencyDetailFragmentArgs.fromBundle(requireArguments())
        val bookName = args.currencyId
        viewModel.downloadOrders(bookName ?: "")
        viewModel.downloadTicker(bookName ?: "")
        bidsAdapter = BidsAdapter()

        initObservers(bookName)
        tabclickListeners()
    }

    private fun initObservers(bookName: String) {
        viewModel.apply {
            error.observe(viewLifecycleOwner, {
                it?.let {
                    Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
                }
            })

            loadingOrders.observe(viewLifecycleOwner, {
                if (!it) {
                    viewModel.getOrder(bookName)
                }
            })

            loadingTicker.observe(viewLifecycleOwner, {
                if (!it) {
                    viewModel.getTicker(bookName)
                }
            })

            bookTicker.observe(viewLifecycleOwner, {
                it?.let {
                    fillDataTicker(it)
                }
            })
            orders.observe(viewLifecycleOwner, {
                it?.let {
                    fillOrderList(it)
                }
            })
        }
    }

    private fun fillDataTicker(data: BookDetail) {
        binding?.apply {
            val currencyCode = data.book.getCurrencyCode()
            detailCurrencyHigh.text = data.high.formatCurrency()
            detailCurrencyLow.text = data.low.formatCurrency()
            detailCurrencyMarket.text = data.book.getmarketFormat()
            detailCurrencyPrice.text = data.last.formatCurrency()

            val imageSize = resources.getDimension(R.dimen.currency_detail_image).toInt()

            Glide.with(detailCurrencyImage)
                .load(IMAGES_URL + currencyCode)
                .fitCenter()
                .placeholder(R.drawable.ic_baseline_monetization_on_24)
                .apply(RequestOptions().override(imageSize, imageSize))
                .into(detailCurrencyImage)
        }
    }

    private fun fillOrderList(data: OrderDetail) {
        binding?.tradesList?.adapter = bidsAdapter
        bidsAdapter.submitList(data.bids)
    }

    private fun fillData(type: TYPES) {
        if (type == TYPES.BIDS) {
            bidsAdapter.submitList(viewModel.orders.value?.bids)
        } else {
            bidsAdapter.submitList(viewModel.orders.value?.asks)
        }
    }

    private fun tabclickListeners() {
        binding?.detailCurrencyTabBids?.setOnClickListener {
            it.background = context?.let { context ->
                ContextCompat.getDrawable(
                    context,
                    R.drawable.borderbottom
                )
            }
            binding?.detailCurrencyTabAsks?.background = null
            fillData(TYPES.BIDS)
        }

        binding?.detailCurrencyTabAsks?.setOnClickListener {
            it.background = context?.let { context ->
                ContextCompat.getDrawable(
                    context,
                    R.drawable.borderbottom
                )
            }
            binding?.detailCurrencyTabBids?.background = null
            fillData(TYPES.ASKS)
        }
    }
}
