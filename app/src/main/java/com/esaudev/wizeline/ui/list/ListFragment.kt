package com.esaudev.wizeline.ui.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.esaudev.wizeline.R
import com.esaudev.wizeline.databinding.FragmentListBinding
import com.esaudev.wizeline.extensions.toast
import com.esaudev.wizeline.model.AvailableBook
import com.esaudev.wizeline.ui.adapters.BookAdapter
import com.esaudev.wizeline.utils.Constants.NETWORK_UNKNOWN_ERROR
import com.esaudev.wizeline.utils.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment(), BookAdapter.OnBookClickListener {

    private var _binding: FragmentListBinding? = null
    private val binding: FragmentListBinding
        get() = _binding!!

    private val viewModel: ListViewModel by viewModels()
    private var listAdapter: BookAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentListBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initObservers()

    }

    private fun init(){
        initComponents()
        viewModel.getAvailableBooks()
    }

    private fun initComponents(){
        listAdapter = BookAdapter(requireContext(), this)
        binding.rvList.adapter = listAdapter
        val linearLayoutManager =  LinearLayoutManager(requireContext())
        binding.rvList.layoutManager = linearLayoutManager
    }

    private fun initObservers(){
        viewModel.getAvailableBooksEvent.observe(viewLifecycleOwner, { dataState ->
            when(dataState){
                is DataState.Loading -> showProgressBar()
                is DataState.Success -> handleSuccess(dataState.data)
                is DataState.Error -> handleError(dataState.error)
                else -> Unit
            }
        })
    }

    private fun handleSuccess(list: List<AvailableBook>){
        hideProgressBar()
        Log.d("TAG_VIEW", list.toString())
        listAdapter?.submitList(list)
    }

    private fun handleError(error: String){
        hideProgressBar()
        when(error){
            NETWORK_UNKNOWN_ERROR -> activity?.toast(getString(R.string.network__unknown_error))
            else -> activity?.toast(getString(R.string.network__unknown_error))
        }
    }

    private fun showProgressBar(){
        binding.pbList.visibility = View.VISIBLE
        binding.rvList.visibility = View.GONE
    }

    private fun hideProgressBar(){
        binding.pbList.visibility = View.GONE
        binding.rvList.visibility = View.VISIBLE
    }

    override fun onBookClickListener(book: AvailableBook) {
        activity?.toast("Book clickeado")
    }

}