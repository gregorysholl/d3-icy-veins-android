package com.example.d3companion.views.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.d3companion.R
import com.example.d3companion.models.D3Build
import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.presenters.list.ListPresenter
import com.example.d3companion.presenters.list.ListPresenterProvider
import com.example.d3companion.services.FileD3IcyVeinsProvider
import com.example.d3companion.views.build.BuildActivity
import kotlinx.android.synthetic.main.activity_list.*

//TODO: Add pull-to-refresh?

class ListActivity : AppCompatActivity(), ListView, ListFragment.Listener {

    private var presenter: ListPresenterProvider? = null

    private var currentFragmentType: D3ViewType = D3ViewType.Class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        presenter = ListPresenter(this, FileD3IcyVeinsProvider(baseContext))
        presenter?.getClasses()
    }

    override fun onBackPressed() {
        when(currentFragmentType) {
            D3ViewType.Class -> super.onBackPressed()
            D3ViewType.Build -> presenter?.getClasses()
        }
    }

    override fun onSelectedItem(item: D3ViewElement) {
        Log.d("DEBUG", item.name)
        when(item.type) {
            D3ViewType.Class -> {
                presenter?.getBuilds(item.name)
            }
            D3ViewType.Build -> {
                presenter?.getBuild(item.name)
            }
        }
    }

    override fun showList(list: List<D3ViewElement>) {
        listActivity_container.visibility = View.VISIBLE
        listActivity_progressBar.visibility = View.GONE

        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.listActivity_container, ListFragment.newInstance(list))
        ft.commit()
        currentFragmentType = list.firstOrNull()?.type ?: D3ViewType.Class
    }

    override fun showBuildActivity(build: D3Build) {
        val intent = Intent(baseContext, BuildActivity::class.java).apply {
            putExtra(BuildActivity.ARG_BUILD, build)
        }
        startActivity(intent)
    }

    override fun showError() {
        listActivity_container.visibility = View.GONE
        listActivity_progressBar.visibility = View.GONE

        //TODO: Show alert
    }
}
