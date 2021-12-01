package com.kcruz.cryptochallenge.presentation.currencydetail.order

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.kcruz.cryptochallenge.databinding.FragmentOrdersBinding
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.presentation.currencydetail.order.adapter.OrderAdapter

class OrdersFragment : Fragment() {

    private var binding: FragmentOrdersBinding? = null
    private var type: String? = null
    private var orders = listOf<Order>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            type = it.getString(ORDER_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOrdersBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    private fun setupView() {
        val orderAdapter = OrderAdapter()
        orderAdapter.submitList(orders)
        binding?.rvOrders?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = orderAdapter
        }
    }

    fun setOrders(_orders: List<Order>) {
        orders = _orders
    }

    companion object {
        private const val ORDER_TYPE = "type"

        @JvmStatic
        fun newInstance(type: String) =
            OrdersFragment().apply {
                arguments = Bundle().apply {
                    putString(ORDER_TYPE, type)
                }
            }
    }
}