package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.sylphid.azurlanecollection.databinding.FragmentStartingPageBinding

class StartingPageFragment: ViewModelFragment() {
    private lateinit var binding: FragmentStartingPageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartingPageBinding.inflate(layoutInflater)

        binding.btnShips.setOnClickListener {
            viewModel.setLoading()
            findNavController().navigate(
                StartingPageFragmentDirections.actionStartingPageToShipListPage()
            )
        }
        return binding.root
    }
}