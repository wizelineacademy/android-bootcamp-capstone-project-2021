package dev.ricsarabia.cryptochallenge.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dev.ricsarabia.cryptochallenge.R
import dev.ricsarabia.cryptochallenge.ui.MainViewModel

class DetailFragment : Fragment() {
    companion object { fun newInstance() = DetailFragment() }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        viewModel.getBookPrices()
        viewModel.getBookOrders()

        viewModel.selectedBookPrices.observe(viewLifecycleOwner, {
            Log.wtf("selectedBookPrices", it.toString())
        })
        viewModel.selectedBookOrders.observe(viewLifecycleOwner, {
            Log.wtf("selectedBookOrders", it.toString())
        })
    }
}