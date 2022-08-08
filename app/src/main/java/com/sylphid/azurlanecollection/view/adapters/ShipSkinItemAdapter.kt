package com.sylphid.azurlanecollection.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sylphid.azurlanecollection.api.Skin
import com.sylphid.azurlanecollection.databinding.ShipSkinItemBinding

class ShipSkinItemAdapter(
    private val selectSkin: (Skin) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var skins = mutableListOf<Skin>()

    fun setSkins(input: List<Skin>){
        skins.addAll(input)
        notifyDataSetChanged()
    }

    inner class SkinViewHolder(private val binding: ShipSkinItemBinding)
        : RecyclerView.ViewHolder(binding.root){
            fun onBind(skin: Skin){
                Glide.with(binding.ivSkin)
                    .load(skin.image)
                    .into(binding.ivSkin)
                binding.root.setOnClickListener {
                    selectSkin(skin)
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkinViewHolder(
            ShipSkinItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is SkinViewHolder -> holder.onBind(skins[position])
        }
    }

    override fun getItemCount(): Int {
        return skins.size
    }

}