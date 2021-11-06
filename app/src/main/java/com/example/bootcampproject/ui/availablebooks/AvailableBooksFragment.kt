package com.example.bootcampproject.ui.availablebooks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bootcampproject.data.mock.Payload
import com.example.bootcampproject.databinding.FragmentAvailableBooksBinding
import dagger.hilt.android.AndroidEntryPoint

private const val VIEW_HOLDER_SCREEN_PROPORTION = 1.0 / 5.0

@AndroidEntryPoint
class AvailableBooksFragmentFragment : Fragment() {
    private var _binding: FragmentAvailableBooksBinding? = null
    private val binding: FragmentAvailableBooksBinding
        get() = _binding!!


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
        availableBooksAdapter= AvailableBooksAdapter {  }
        val books=requireArguments()
       // val temp =books.getParcelable<Payload>("Bundle")
        binding.availableBooksList.run {
            adapter = availableBooksAdapter
            layoutManager = object : LinearLayoutManager(requireContext()) {
                override fun checkLayoutParams(lp: RecyclerView.LayoutParams?): Boolean {
                    lp?.height = (height * VIEW_HOLDER_SCREEN_PROPORTION).toInt()
                    return true
                }
            }
        }
       // availableBooksAdapter.submitList(mockPokemons)

    }
}