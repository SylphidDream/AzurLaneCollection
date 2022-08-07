package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.sylphid.azurlanecollection.R
import com.sylphid.azurlanecollection.databinding.FragmentShipDetailsBinding

class ShipDetailsFragment: ViewModelFragment() {

    private lateinit var binding: FragmentShipDetailsBinding
    private val args: ShipDetailsFragmentArgs by navArgs()

    private val levelArray = mutableListOf<String>(
        "Level 100",
        "Level 100 Kai",
        "Level 120",
        "Level 120 Kai",
        "Level 125",
        "Level 125 Kai"
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipDetailsBinding.inflate(layoutInflater)
        setUpUI()

        return binding.root
    }

    fun setUpUI(){
        binding.apply{
            binding.ibBackButton.setOnClickListener {
                findNavController().navigate(
                    ShipDetailsFragmentDirections.actionShipDetailsPageToShipListPage()
                )
            }
            binding.btn100.setOnClickListener{
                //check if retrofit
                //change stat table
            }
            binding.btn120.setOnClickListener{
                //check if retrofit
                //change stat table
            }
            binding.btn125.setOnClickListener{
                //check if retrofit
                //change stat table
            }
            binding.cbRetrofit.setOnClickListener {
                //find current stats selection
                //update stats to reflect
                //update backgrounds based on ship
            }

            //Set colors based on rarity, pull default skin, set retrofit to visible or gone
        }
    }

}