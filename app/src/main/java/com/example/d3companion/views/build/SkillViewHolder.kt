package com.example.d3companion.views.build

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.d3companion.R
import com.example.d3companion.models.D3Details
import kotlinx.android.synthetic.main.fragment_skill.view.*

class SkillViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val textView: TextView = view.skillFragment_skillName
    private val imageView: ImageView = view.skillFragment_skillImage

    fun bindData(detail: D3Details) {
        val hasName = !detail.name.isEmpty()

        textView.text = if (hasName) detail.name else view.context.getString(R.string.skill_rune_any_choice)
        if (hasName) {
            Glide.with(view).load(detail.image).into(imageView)
        } else {
            imageView.visibility = View.GONE
        }
    }
}
