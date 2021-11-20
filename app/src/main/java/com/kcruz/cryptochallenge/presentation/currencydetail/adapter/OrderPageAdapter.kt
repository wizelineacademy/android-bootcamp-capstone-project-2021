package com.kcruz.cryptochallenge.presentation.currencydetail.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.kcruz.cryptochallenge.domain.Order
import com.kcruz.cryptochallenge.presentation.currencydetail.order.OrderType
import com.kcruz.cryptochallenge.presentation.currencydetail.order.OrdersFragment
import com.kcruz.cryptochallenge.presentation.empty.EmptyFragment

class OrderPageAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    private var orders = mapOf<String, List<Order>>()

    fun setOrders(_orders: Map<String, List<Order>>) {
        orders = _orders
    }

    override fun getItemCount() = orders.size

    override fun createFragment(position: Int): Fragment =
        when (position) {
            OrderType.ASKS.page -> {
                val fragment = OrdersFragment.newInstance(OrderType.ASKS.label)
                fragment.setOrders(orders[OrderType.ASKS.label] ?: listOf())
                fragment
            }
            OrderType.BIDS.page -> {
                val fragment = OrdersFragment.newInstance(OrderType.BIDS.label)
                fragment.setOrders(orders[OrderType.BIDS.label] ?: listOf())
                fragment
            }
            else -> EmptyFragment.newInstance()
        }
}