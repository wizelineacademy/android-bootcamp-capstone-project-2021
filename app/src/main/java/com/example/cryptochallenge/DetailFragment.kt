package com.example.cryptochallenge

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.cryptochallenge.common.*
import com.example.cryptochallenge.common.delegates.MoneyFormatDelegate
import com.example.cryptochallenge.common.getConversionName
import com.example.cryptochallenge.common.makeToast
import com.example.cryptochallenge.common.toMoneyFormat
import com.example.cryptochallenge.data.model.*
import com.example.cryptochallenge.databinding.FragmentDetailBinding
import com.example.cryptochallenge.ui.adapter.OrderItemAdapter
import com.example.cryptochallenge.ui.adapter.toOrderItem
import com.example.cryptochallenge.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val TAG = this.javaClass.simpleName
    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding get() = _binding!!
    private val viewModel: DetailViewModel by viewModels()

    private var askOrdersAdapter: OrderItemAdapter? = null
    private var bidOrdersAdapter: OrderItemAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setBookName(it.getString("ID", "-"))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponents()
        initObservers()
        getBookDetail()
    }

    private fun initComponents() {
        binding.toolBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        askOrdersAdapter = OrderItemAdapter(requireContext())
        binding.recyclerViewAsks.adapter = askOrdersAdapter
        binding.recyclerViewAsks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        bidOrdersAdapter = OrderItemAdapter(requireContext())
        binding.recyclerViewBids.adapter = bidOrdersAdapter
        binding.recyclerViewBids.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun initObservers() {
        viewModel.getBookDetailEvent.observe(viewLifecycleOwner, { result ->
            when (result) {
                is GetBookDetailSuccess -> {
                    setBookInformation(result.data)
                }

                is GetBookDetailFailure -> {
                    makeToast("Something went wrong: ${result.exception.localizedMessage}")
                }
            }
        });

        viewModel.getBookOrdersEvent.observe(viewLifecycleOwner, { result ->
            when (result) {
                is GetBookOrdersSuccess -> {
                    setBookOrdersInformation(result.data)
                }
            }
        })
    }

    private fun setBookInformation(book: Book) = with(binding) {
        textViewName.text = book.getConversionName()

        Glide.with(requireContext())
            .load(book.getCryptoImage())
            .circleCrop()
            .into(imageViewImage)

        var moneyFormatted by MoneyFormatDelegate()
        moneyFormatted = book.last
        textViewLastPrice.text = moneyFormatted
        //textViewLastPrice.text = book.last.toMoneyFormat() // Commented to show the use of delegates
        textViewLastHigh.text = book.high.toMoneyFormat()
        textViewLastLow.text = book.low.toMoneyFormat()
    }

    private fun setBookOrdersInformation(bookOrders: BookOrders) = with(binding) {
        askOrdersAdapter?.submitList(bookOrders.asks.map { it.toOrderItem() })
        bidOrdersAdapter?.submitList(bookOrders.bids.map { it.toOrderItem() })
    }

    private fun getBookDetail() {
        viewModel.getBookDetail()
        viewModel.getBookOrders()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}





















