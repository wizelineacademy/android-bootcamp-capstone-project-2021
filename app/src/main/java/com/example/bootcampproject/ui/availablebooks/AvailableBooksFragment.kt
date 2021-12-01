package com.example.bootcampproject.ui.availablebooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.AvailableBook
import com.example.bootcampproject.databinding.FragmentAvailableBooksBinding
import com.example.bootcampproject.util.isOnline
import dagger.hilt.android.AndroidEntryPoint

private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 5.0

@AndroidEntryPoint
class AvailableBooksFragmentFragment : Fragment() {
    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding: FragmentAvailableBooksBinding
        get() = _binding!!

    private val viewModel: AvailablebooksViewModel by viewModels()

    private lateinit var navController: NavController

    private lateinit var availableBooksAdapter: AvailableBooksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentAvailableBooksBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        availableBooksAdapter = AvailableBooksAdapter { codeBook ->
            AvailableBooksFragmentFragmentDirections
                .toorderBooksFragment(codeBook)
                .let { navController.navigate(it) }
        }
        navController = findNavController()
        val code = requireArguments().getString("code")
        viewModel.getAvailableBooks(code, checkConection())
        viewModel.books.observe(viewLifecycleOwner, { books ->
            fillInfoBooks(books)
        })
    }

    fun fillInfoBooks(books: List<AvailableBook>) {
        binding.availableBooksList.run {
            adapter = availableBooksAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
        }
        availableBooksAdapter.submitList(books)
    }

    private fun checkConection(): Boolean {
        return isOnline(requireContext())
    }
}
