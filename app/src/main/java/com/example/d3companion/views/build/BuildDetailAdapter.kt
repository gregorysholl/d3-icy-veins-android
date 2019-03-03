package com.example.d3companion.views.build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.d3companion.R
import com.example.d3companion.models.D3Details
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3SkillSet
import kotlin.math.floor

class BuildDetailAdapter(
    private val gear: List<D3Item>,
    private val skillSet: D3SkillSet
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val GEAR = 0
        private const val SKILL = 1
    }

    private val activeSkillSize = skillSet.active.size * 2

    override fun getItemViewType(position: Int): Int {
        return if (position < 1) GEAR else SKILL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GEAR -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_gear, parent, false)
                GearViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_skill, parent, false)
                SkillViewHolder(view)
            }
        }
    }

    //just one fragment for gears
    override fun getItemCount(): Int = 1 + activeSkillSize + skillSet.passive.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            GEAR -> {
                val gearHolder = holder as GearViewHolder
                gearHolder.bindData(gear)
            }
            else -> {
                val detail = getDetail(position - 1)
                val skillHolder = holder as SkillViewHolder
                skillHolder.bindData(detail)
            }
        }
    }

    private fun getDetail(position: Int): D3Details {
        return if (position < activeSkillSize) {
            val activeSkill = skillSet.active[floor((position / 2).toDouble()).toInt()]
            if (position % 2 == 0) {
                activeSkill.skill
            } else {
                activeSkill.rune
            }
        } else {
            skillSet.passive[position - activeSkillSize]
        }
    }
}
