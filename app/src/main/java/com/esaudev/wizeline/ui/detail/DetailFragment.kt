package com.esaudev.wizeline.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.esaudev.wizeline.R
import com.esaudev.wizeline.databinding.FragmentDetailBinding
import com.esaudev.wizeline.databinding.FragmentListBinding

class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding: FragmentDetailBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentDetailBinding
            .inflate(layoutInflater, container, false)
            .apply { _binding = this }
            .root
    }

}