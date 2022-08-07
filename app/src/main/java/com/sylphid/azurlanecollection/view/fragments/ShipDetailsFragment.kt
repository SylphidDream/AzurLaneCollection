package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.sylphid.azurlanecollection.R
import com.sylphid.azurlanecollection.api.ShipEntity
import com.sylphid.azurlanecollection.databinding.FragmentShipDetailsBinding
import com.sylphid.azurlanecollection.model.UIState

class ShipDetailsFragment: ViewModelFragment() {

    private lateinit var binding: FragmentShipDetailsBinding
    private val args: ShipDetailsFragmentArgs by navArgs()
    private val TAG = "ShipDetailsFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipDetailsBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    fun configureObserver(){
        viewModel.shipId.observe(viewLifecycleOwner) { state ->
            when(state){
                is UIState.Loading -> {
                    viewModel.setSuccessForDetails(args.shipListItem)
                }
                is UIState.Success -> {
                    val ship = state.response[0];
                    //code goes here
                    setUpUI(ship)

                }
                else -> {
                    Log.e(TAG, "configureObserver: ", )
                }
            }
        }
    }

    fun setUpUI(ship: ShipEntity){
        binding.apply{
            binding.ibBackButton.setOnClickListener {
                findNavController().navigate(
                    ShipDetailsFragmentDirections.actionShipDetailsPageToShipListPage()
                )
            }
            binding.btnBase.setOnClickListener {

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

    fun importShipDetails(){
    }

}