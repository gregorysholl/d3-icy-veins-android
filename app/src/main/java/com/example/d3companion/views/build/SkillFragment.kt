package com.example.d3companion.views.build

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide

import com.example.d3companion.R
import com.example.d3companion.models.D3ActiveSkill
import kotlinx.android.synthetic.main.fragment_skill.*

class SkillFragment : Fragment() {

    private var activeSkill: D3ActiveSkill? = null

    companion object {

        const val ARG_SKILL = "fragment-skill-activeSkill"

        @JvmStatic
        fun newInstance(skill: D3ActiveSkill) =
            SkillFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_SKILL, skill)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            activeSkill = it.getParcelable(ARG_SKILL)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_skill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
    }

    private fun setupView() {
        val hasRune = activeSkill?.rune?.name?.isEmpty() == false

        setupTexts(hasRune)
        setupImages(hasRune)
    }

    private fun setupTexts(showRune: Boolean) {
        skillFragment_skillName.text = activeSkill?.skill?.name
//        skillFragment_runeName.text = if (showRune) activeSkill?.rune?.name else getString(R.string.skill_rune_any_choice)
    }

    private fun setupImages(showRune: Boolean) {
        Glide.with(this).load(activeSkill?.skill?.image).into(skillFragment_skillImage)
//        if (showRune) {
//            Glide.with(this).load(activeSkill?.rune?.image).into(skillFragment_runeImage)
//        } else {
//            skillFragment_runeImage.visibility = View.GONE
//        }
    }
}
