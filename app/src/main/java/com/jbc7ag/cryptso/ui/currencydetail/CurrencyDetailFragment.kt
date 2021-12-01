package com.jbc7ag.cryptso.ui.currencydetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.jbc7ag.cryptso.data.model.BookDetail
import com.jbc7ag.cryptso.data.model.OrderDetail
import com.jbc7ag.cryptso.databinding.FragmentCurrencyDetailsBinding
import com.jbc7ag.cryptso.R
import dagger.hilt.android.AndroidEntryPoint
import android.graphics.drawable.BitmapDrawable
import com.jbc7ag.cryptso.util.*
import java.util.Locale


@AndroidEntryPoint
class CurrencyDetailFragment : Fragment() {

    private var _binding: FragmentCurrencyDetailsBinding? = null
    private val binding get() = _binding
    private val viewModel: CurrencyDetailViewModel by viewModels()
    private lateinit var bidsAdapter: BidsAdapter
    private var palette: Palette? = null
    private var dominantColor: Int? = null

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
        initObservers(bookName)
        initView(bookName)
        initObservers(bookName)
        buttonListeners()
    }

    private fun initView(bookName: String) {
        viewModel.apply {
            downloadOrders(bookName)
            downloadTicker(bookName)
            getBookName(bookName)
        }
        bidsAdapter = BidsAdapter()
    }

    private fun initObservers(bookName: String) {
        viewModel.apply {
            coinName.observe(viewLifecycleOwner, {
                it?.let {
                    val mActionBarToolbar: Toolbar? = activity?.findViewById(R.id.toolbar)
                    mActionBarToolbar?.title = it.replaceFirstChar { title ->
                        if (title.isLowerCase()) title.titlecase(
                            Locale.getDefault()
                        ) else title.toString()
                    }.replace("-", " ")
                }
            })
            error.observe(viewLifecycleOwner, {
                it?.let {
                    showEmptyScreen(true)
                }
            })
            loadingOrders.observe(viewLifecycleOwner, {
                if (!it) {
                    viewModel.getOrder(bookName)
                }
                binding?.progressLoader?.visibility = if(it) View.VISIBLE else View.GONE
            })
            loadingTicker.observe(viewLifecycleOwner, {
                if (!it) {
                    viewModel.getTicker(bookName)
                }
            })
            bookTicker.observe(viewLifecycleOwner, {
                it?.let {
                    fillDataTicker(it)
                    showEmptyScreen(false)
                }
            })
            orders.observe(viewLifecycleOwner, {
                it?.let {
                    fillOrderList(it)
                    showEmptyScreen(false)
                }
            })
        }
    }

    private fun fillDataTicker(data: BookDetail) {
        binding?.apply {
            val currencyCode = data.book.getCurrencyCode()
            detailCurrencyHigh.text = getString(R.string.show_money, data.high.formatCurrency(), data.book.getCurrencyCodeFilter().uppercase())
            detailCurrencyLow.text = getString(R.string.show_money, data.low.formatCurrency(), data.book.getCurrencyCodeFilter().uppercase())
            detailCurrencyPrice.text = getString(R.string.show_money, data.last.formatCurrency(), data.book.getCurrencyCodeFilter().uppercase())
            detailCurrencyCode.text = currencyCode.uppercase()

            Glide.with(detailCurrencyImage)
                .load(IMAGES_URL + currencyCode)
                .fitCenter()
                .placeholder(R.drawable.ic_baseline_monetization_on_24)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: Target<Drawable>?, p3: Boolean): Boolean = false
                    override fun onResourceReady(p0: Drawable?, p1: Any?, p2: Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                        val bitmap = (p0 as? BitmapDrawable)?.bitmap
                        palette = bitmap?.let { Palette.from(it).generate() }
                        dominantColor = palette?.getDominantColor(ContextCompat.getColor(requireContext(), R.color.white))
                        val colorToUse = dominantColor
                        context?.let{
                            if(colorToUse != null) {
                                if (!isDark(colorToUse)) {
                                    detailCurrencyLabel.setTextColor(colorToUse)
                                    detailCurrencyHighLabel.setTextColor(colorToUse)
                                    detailCurrencyLowLabel.setTextColor(colorToUse)
                                }
                                buttonBids.setSelected(colorToUse)
                            }
                        }
                        return false
                    }
                })
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

    private fun buttonListeners() {

        binding?.buttonBids?.setOnClickListener{
            dominantColor?.let { color ->
                binding?.buttonBids?.setSelected(color)
                binding?.buttonAsks?.setUnSelected()
            }
            fillData(TYPES.BIDS)
        }

        binding?.buttonAsks?.setOnClickListener{
            dominantColor?.let { color ->
                binding?.buttonAsks?.setSelected(color)
                binding?.buttonBids?.setUnSelected()
            }
            fillData(TYPES.ASKS)
        }
    }

    private fun showEmptyScreen(visible: Boolean){
        val itemsVisibility = if(!visible) View.VISIBLE else View.GONE
        binding?.noItemsView?.root?.visibility =  if(visible) View.VISIBLE else View.GONE
        binding?.header?.visibility = itemsVisibility
        binding?.buttonBids?.visibility = itemsVisibility
        binding?.buttonAsks?.visibility = itemsVisibility
        binding?.detailCurrencyTrades?.visibility = itemsVisibility
    }
}
