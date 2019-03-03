package com.example.d3companion.views.build

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3ItemDescription
import kotlinx.android.synthetic.main.fragment_gear.view.*

class GearViewHolder(
    private val view: View
) : RecyclerView.ViewHolder(view) {

    private val helmetImage: ImageView = view.gearFragment_helmetImage
    private val chestImage: ImageView = view.gearFragment_chestImage
    private val beltImage: ImageView = view.gearFragment_beltImage
    private val pantsImage: ImageView = view.gearFragment_pantsImage

    fun bindData(list: List<D3Item>) {
        for (item in list) {
            val imageView = getImageView(item.description)
            if (imageView != null) {
                Glide.with(view)
                    .load(item.main.image)
                    .into(imageView)
            }
        }
    }

    private fun getImageView(description: D3ItemDescription): ImageView? {
        return when (description) {
            D3ItemDescription.Helmet -> helmetImage
            D3ItemDescription.Chest -> chestImage
            D3ItemDescription.Belt -> beltImage
            D3ItemDescription.Pants -> pantsImage
            else -> null
        }
    }
}
