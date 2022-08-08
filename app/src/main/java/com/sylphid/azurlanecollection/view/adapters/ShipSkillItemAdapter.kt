package com.sylphid.azurlanecollection.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sylphid.azurlanecollection.api.Skill
import com.sylphid.azurlanecollection.api.Skin
import com.sylphid.azurlanecollection.databinding.ShipSkillItemBinding
import com.sylphid.azurlanecollection.databinding.ShipSkinItemBinding

class ShipSkillItemAdapter(
    private val selectSkill: (Skill) -> Unit
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var skills = mutableListOf<Skill>()

    fun setSkills(input: List<Skill>) {
        skills.addAll(input)
        notifyDataSetChanged()
    }

    inner class SkillViewHolder(private val binding: ShipSkillItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(skill: Skill) {
            Glide.with(binding.ivSkillIcon)
                .load(skill.icon)
                .into(binding.ivSkillIcon)
            binding.tvSkillName.text = skill.names!!.en
            binding.root.setOnClickListener {
                selectSkill(skill)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return SkillViewHolder(
            ShipSkillItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is SkillViewHolder -> holder.onBind(skills[position])
        }
    }

    override fun getItemCount(): Int {
        return skills.size
    }
}