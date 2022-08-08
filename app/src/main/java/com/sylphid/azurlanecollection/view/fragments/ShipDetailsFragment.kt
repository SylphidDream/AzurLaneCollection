package com.sylphid.azurlanecollection.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.sylphid.azurlanecollection.R
import com.sylphid.azurlanecollection.api.ShipEntity
import com.sylphid.azurlanecollection.api.Skill
import com.sylphid.azurlanecollection.api.Skin
import com.sylphid.azurlanecollection.api.StatDetails
import com.sylphid.azurlanecollection.databinding.FragmentShipDetailsBinding
import com.sylphid.azurlanecollection.model.UIState
import com.sylphid.azurlanecollection.view.adapters.ShipListItemAdapter
import com.sylphid.azurlanecollection.view.adapters.ShipSkillItemAdapter
import com.sylphid.azurlanecollection.view.adapters.ShipSkinItemAdapter

class ShipDetailsFragment : ViewModelFragment() {

    private lateinit var binding: FragmentShipDetailsBinding
    private val args: ShipDetailsFragmentArgs by navArgs()
    private val TAG = "ShipDetailsFragment"
    private val BTN100 = 100
    private val BTN120 = 120
    private val BTN125 = 125
    private val BTNBASE = 0

    private val shipSkinItemAdapter by lazy {
        ShipSkinItemAdapter(selectSkin = ::selectSkin)
    }

    private val shipSkillItemAdapter by lazy {
        ShipSkillItemAdapter(selectSkill= ::selectSkill)
    }

    private var currentStats = 0


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShipDetailsBinding.inflate(layoutInflater)
        configureObserver()
        return binding.root
    }

    fun configureObserver() {
        viewModel.shipId.observe(viewLifecycleOwner) { state ->
            when (state) {
                is UIState.Loading -> {
                    viewModel.setSuccessForDetails(args.shipListItem)
                }
                is UIState.Success -> {
                    val ship = state.response[0];
                    //code goes here
                    setUpUI(ship)
                    shipSkinItemAdapter.setSkins(ship.skins!!)
                    binding.rvSkinCarousel.adapter = shipSkinItemAdapter
                    shipSkillItemAdapter.setSkills(ship.skills!!)
                    binding.rvSkillCarousel.adapter = shipSkillItemAdapter
                }
                else -> {
                    Log.e(TAG, "configureObserver: ")
                }
            }
        }
    }

    fun setUpUI(ship: ShipEntity) {
        binding.apply {

            tvShipName.text = ship.names!!.en
            tvShipCodeAndClass.text = "${ship.names.code} - ${ship.hullType}"
            if (ship.retrofit != true) {
                cbRetrofit.visibility = View.GONE
            }
            when (ship.rarity) {
                "Normal" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_common))
                "Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_rare))
                "Elite" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_elite))
                "Super Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_super_rare))
                "Ultra Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_ultra_rare))
                "Priority" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_priority))
                "Decisive" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_decisive))
            }

            //Set initial stats
            setStats(ship.stats!!.baseStats!!, ship.hullType)

            //set initial skin view
            selectSkin(ship.skins!![0])

            //set initial Skill
            selectSkill(ship.skills!![0])


            binding.ibBackButton.setOnClickListener {
//                viewModel.setLoading()
                findNavController().popBackStack()
            }
            binding.btnBase.setOnClickListener {
                if(cbRetrofit.isChecked){
                    cbRetrofit.isChecked = false
                    cbRetrofit.callOnClick()
                }
                var baseStats = ship.stats!!.baseStats!!
                setStats(baseStats, ship.hullType)
                currentStats = BTNBASE
            }
            binding.btn100.setOnClickListener {
                //check if retrofit
                lateinit var level100Stats: StatDetails
                if (cbRetrofit.isChecked) {
                    level100Stats = ship.stats!!.level100Retrofit!!
                } else {
                    level100Stats = ship.stats!!.level100!!
                }
                setStats(level100Stats, ship.hullType)
                currentStats = BTN100

            }
            binding.btn120.setOnClickListener {
                //check if retrofit
                //change stat table
                lateinit var level120Stats: StatDetails
                if (cbRetrofit.isChecked) {
                    level120Stats = ship.stats!!.level120Retrofit!!
                } else {
                    level120Stats = ship.stats!!.level120!!
                }
                setStats(level120Stats, ship.hullType)
                currentStats = BTN120
            }
            binding.btn125.setOnClickListener {
                //check if retrofit
                //change stat table
                lateinit var level125Stats: StatDetails
                if (cbRetrofit.isChecked) {
                    level125Stats = ship.stats!!.level125Retrofit!!
                } else {
                    level125Stats = ship.stats!!.level125!!
                }
                setStats(level125Stats, ship.hullType)
                currentStats = BTN125
            }
            binding.cbRetrofit.setOnClickListener {
                //find current stats selection
                //update stats to reflect
                //update backgrounds based on ship
                if(currentStats == BTN100){
                    btn100.callOnClick()
                } else if(currentStats == BTN120){
                    btn120.callOnClick()
                } else if(currentStats == BTN125){
                    btn125.callOnClick()
                }
                if (cbRetrofit.isChecked) {
                    when (ship.rarity) {
                        "Normal" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_rare))
                        "Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_elite))
                        "Elite" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_super_rare))
                        "Super Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_ultra_rare))
                    }
                } else {
                    when (ship.rarity) {
                        "Normal" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_common))
                        "Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_rare))
                        "Elite" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_elite))
                        "Super Rare" -> root.setBackgroundColor(root.context.getColor(R.color.azur_lane_super_rare))
                    }
                }
            }

            //Set colors based on rarity, pull default skin, set retrofit to visible or gone
        }
    }

    fun setStats(stats: StatDetails, hullType: String?) {
        binding.apply {
            tvHp.text = "HP: ${stats.health}"
            tvFp.text = "FP: ${stats.firepower}"
            tvAa.text = "AA: ${stats.antiair}"
            tvArmor.text = "Armor: ${stats.armor}"
            tvTrp.text = "TRP: ${stats.torpedo}"
            tvAvi.text = "AVI: ${stats.aviation}"
            tvLck.text = "LCK: ${stats.luck}"
            tvRld.text = "RLD: ${stats.reload}"
            tvEva.text = "EVA: ${stats.evasion}"
            tvCost.text = "Cost: ${stats.oilConsumption}"
            if (hullType == "Submarine" || hullType == "Submarine Carrier") {
                tvAswOxy.text = "OXY: ${stats.oxygen}"
                tvAmo.text = "AMO: ${stats.ammunition}"
                tvAmo.visibility = View.VISIBLE
            } else {
                tvAswOxy.text = "ASW: ${stats.antisubmarineWarfare}"
            }
        }
    }

    fun selectSkin(skin: Skin) {
        binding.apply {
            Glide.with(ivSkinBackground)
                .load(skin.background)
                .into(ivSkinBackground)
            Glide.with(ivSkin)
                .load(skin.image)
                .into(ivSkin)
            Glide.with(ivSkinChibi)
                .load(skin.chibi)
                .into(ivSkinChibi)
        }
    }

    fun selectSkill(skill: Skill){
        binding.apply {
            tvSkillName.text = skill.names!!.en
            tvSkillDesc.text = skill.description
            Glide.with(ivSkillIcon)
                .load(skill.icon)
                .into(ivSkillIcon)
        }
    }

}