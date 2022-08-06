package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.sylphid.azurlanecollection.databinding.FragmentShipsListBinding

private const val GONE = View.GONE
private const val VISIBLE = View.VISIBLE

class ShipsListFragment: ViewModelFragment() {
    private lateinit var binding: FragmentShipsListBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipsListBinding.inflate(layoutInflater)

        binding.ibBackButton.setOnClickListener{
            findNavController().navigate(
                ShipsListFragmentDirections.actionShipListPageToStartingPage()
            )
        }

        binding.ibSearch.setOnClickListener {
            if (binding.tbSearchBar.visibility == VISIBLE){
                binding.tbSearchBar.visibility = GONE
            } else {
                binding.tbSearchBar.visibility = VISIBLE
            }
        }

        binding.ibCancelSearch.setOnClickListener {
            binding.etSearchText.text.clear()
            binding.ibCancelSearch.visibility = GONE
        }

        binding.etSearchText.addTextChangedListener {
            if(binding.ibCancelSearch.visibility == GONE){
                binding.ibCancelSearch.visibility = VISIBLE
            }
            //Perform ship query on currently displayed ships
        }

        binding.btnSearchSubmit.setOnClickListener {
            //Perform ship query, looking for ships that meet specified criteria
        }

        return binding.root
    }
}