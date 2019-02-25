package com.example.d3companion.views.build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.d3companion.R
import com.example.d3companion.models.D3Build
import com.example.d3companion.models.D3Class
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_build.*
import java.lang.RuntimeException

class BuildActivity : AppCompatActivity() {

    private lateinit var buildInfo: D3Build

    companion object {
        const val ARG_BUILD = "build-activity-build"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_build)

//        val build = intent.extras?.getParcelable<D3Build>(ARG_BUILD) ?: throw RuntimeException("BuildActivity expected D3Build as argument")
//        buildInfo = build
        initD3Build()

        setupView()
    }

    private fun setupView() {
        supportActionBar?.title = buildInfo.className
        supportActionBar?.subtitle = buildInfo.tier + " Build"

        buildActivity_name.text = buildInfo.name

        addSkillFragments()
    }

    private fun addSkillFragments() {
        for (activeSkill in buildInfo.skills.active) {
            val ft = supportFragmentManager.beginTransaction()
            ft.add(R.id.buildActivity_skillContainer, SkillFragment.newInstance(activeSkill))
            ft.commit()
        }
    }

    private fun initD3Build() {
        val inputStream = baseContext.resources?.openRawResource(R.raw.builds)

        val text = inputStream?.bufferedReader().use { it?.readText() } ?: return

        val gson = GsonBuilder().setPrettyPrinting().create()
        val listType = object : TypeToken<List<D3Class>>() { }.type

        val list = gson.fromJson<List<D3Class>>(text, listType)

        buildInfo = list.firstOrNull()?.builds?.firstOrNull() ?: throw RuntimeException()
    }
}
