package com.sylphid.azurlanecollection.view.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sylphid.azurlanecollection.R
import com.sylphid.azurlanecollection.api.Name
import com.sylphid.azurlanecollection.api.ShipEntity
import com.sylphid.azurlanecollection.databinding.ShipListItemBinding

private val TAG = "ShipListItemAdapter"

class ShipListItemAdapter(
    private val list: MutableList<ShipEntity?> = mutableListOf(LAFFEY),
    private val openDetails: (ShipEntity) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    fun setShipList(shipList: List<ShipEntity>){
        if(list.isNotEmpty()) list.removeLast()
        list.addAll(shipList)
        notifyDataSetChanged()
    }


    inner class ShipViewHolder(private val binding: ShipListItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun onBind(ship: ShipEntity){
                Log.d(TAG, "onBind: ${ship.names!!.en}")
                binding.apply {
                    tvShipName.text = ship.names?.en
                    Glide.with(ivThumbnail)
                        .load(ship.thumbnail)
                        .into(ivThumbnail)
                    when(ship.rarity){
                        "Normal" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_common))
                        "Rare"->cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_rare))
                        "Elite" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_elite))
                        "Super Rare" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_super_rare))
                        "Ultra Rare" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_ultra_rare))
                        "Priority" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_priority))
                        "Decisive" -> cvCardView.setCardBackgroundColor(root.context.getColor(R.color.azur_lane_decisive))
                    }
                    root.setOnClickListener{
                        openDetails(ship)
                    }
                }?:{
                    Log.e(TAG, "onBind: ${ship.id}", )
                }
            }
        }

    companion object{
        const val LOADING = 1
        const val SHIP = 2

        val LAFFEY = ShipEntity(
            names= Name(en="Laffey"),
            rarity = "Elite",
            thumbnail = "https://raw.githubusercontent.com/AzurAPI/azurapi-js-setup/master/images/skins/019/thumbnail.png"
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ShipViewHolder(
            ShipListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ShipViewHolder -> holder.onBind(list[position]?: LAFFEY)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if(list[position] == null) LOADING else SHIP
    }

    override fun getItemCount(): Int {
        return list.size
    }
}