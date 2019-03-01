package com.example.d3companion.views.build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.d3companion.R
import com.example.d3companion.models.D3Details
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3SkillSet
import kotlinx.android.synthetic.main.fragment_skill.view.*
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
        return SKILL
//        return if (position < 1) GEAR else SKILL
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            GEAR -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_item, parent, false)
                GearViewHolder(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_skill, parent, false)
                SkillViewHolder(view)
            }
        }
    }

    //just one fragment for gears
    override fun getItemCount(): Int = activeSkillSize + skillSet.passive.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            GEAR -> {
                val gearHolder = holder as GearViewHolder

            }
            else -> {
                val detail = getDetail(position)
                val hasName = !detail.name.isEmpty()

                val skillHolder = holder as SkillViewHolder
                skillHolder.textView.text = if (hasName) detail.name else "Any choice"
                if (hasName) {
                    Glide.with(skillHolder.view).load(detail.image).into(skillHolder.imageView)
                } else {
                    skillHolder.imageView.visibility = View.GONE
                }
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

    inner class GearViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
    }

    inner class SkillViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        val textView: TextView = view.skillFragment_skillName
        val imageView: ImageView = view.skillFragment_skillImage
    }
}