package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import com.sylphid.azurlanecollection.api.ShipEntity
import com.sylphid.azurlanecollection.databinding.FragmentShipsListBinding
import com.sylphid.azurlanecollection.model.UIState
import com.sylphid.azurlanecollection.view.adapters.ShipListItemAdapter

private const val GONE = View.GONE
private const val VISIBLE = View.VISIBLE
private const val TAG = "ShipsListFragment"

class ShipsListFragment: ViewModelFragment() {
    private lateinit var binding: FragmentShipsListBinding

    private val shipListItemAdapter by lazy {
        ShipListItemAdapter(openDetails = ::openDetails)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipsListBinding.inflate(layoutInflater)
        setupUI()
        setupShipList()

        return binding.root
    }

    fun setupUI(){
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
            viewModel.setLoading()
            //Perform ship query, looking for ships that meet specified criteria
        }
    }

    fun setupShipList(){
        viewModel.shipListData.observe(viewLifecycleOwner){ uiState ->
            when(uiState){
                is UIState.Loading ->{
                    binding.rvShipList.adapter = shipListItemAdapter
                    viewModel.getShips()
                    Log.d(TAG, "setupShipList: start getting ships")
                }
                is UIState.Error ->
                    Log.e("ShipsListFragment", "setupShipList: ${uiState.error.message}", uiState.error)
                is UIState.Success -> {
                    Log.d(TAG, "setupShipList: Start Posting Ships")
                    binding.apply {
                        shipListItemAdapter.setShipList(uiState.response)
                    }
                }
            }
        }
    }

    fun openDetails(ship: ShipEntity){
        viewModel.setLoadingForDetails()
        findNavController().navigate(
            ShipsListFragmentDirections.actionShipListPageToShipDetailsPage(
                ship
            )
        )
    }
}