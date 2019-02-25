package com.example.d3companion.views.build

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        skillFragment_skillName.text = activeSkill?.skill?.name

        if (activeSkill?.rune?.name?.isEmpty() == false) {
            skillFragment_runeName.text = activeSkill?.rune?.name
        } else {
            skillFragment_runeContainer.visibility = View.GONE
        }
    }
}
