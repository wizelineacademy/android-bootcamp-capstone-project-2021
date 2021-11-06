package com.kcruz.cryptochallenge.presentation.currencydetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.navArgs
import com.kcruz.cryptochallenge.R
import com.kcruz.cryptochallenge.databinding.FragmentCurrencyDetailBinding
import com.kcruz.cryptochallenge.domain.Book

//TODO: Add documentation

class CurrencyDetailFragment : Fragment() {

    private val args: CurrencyDetailFragmentArgs by navArgs()
    private var binding: FragmentCurrencyDetailBinding? = null
    private val viewModel by viewModels<CurrencyDetailViewModel> ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrencyDetailBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val book = args.id
        //viewModel.start(book)
        setViewModelListeners()
    }

    private fun setViewModelListeners() {
        viewModel.currencyDetail.observe(viewLifecycleOwner) {
            setCurrencyDetailInfo(it)
        }

        viewModel.events.observe(viewLifecycleOwner) { event ->
            when(event) {
                is SendMessage -> showMessage(event.message)
            }
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
    }

    //TODO: Set detail data
    private fun setCurrencyDetailInfo(book: Book) {
        binding?.tvCurrencyDetail?.text = book.book

    }
}