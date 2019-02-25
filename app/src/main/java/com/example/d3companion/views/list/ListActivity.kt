package com.example.d3companion.views.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d3companion.R
import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.presenters.list.ListPresenter
import com.example.d3companion.presenters.list.ListPresenterProvider
import com.example.d3companion.services.FileD3IcyVeinsProvider
import com.example.d3companion.views.build.BuildActivity

class ListActivity : AppCompatActivity(), ListView, ListFragment.Listener {

    private var presenter: ListPresenterProvider? = null

    private var currentFragmentType: D3ViewType = D3ViewType.Class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        presenter = ListPresenter(this, FileD3IcyVeinsProvider(baseContext))
    }

    override fun onBackPressed() {
        when(currentFragmentType) {
            D3ViewType.Class -> super.onBackPressed()
            D3ViewType.Build -> replaceFragmentToType(D3ViewType.Class)
        }
    }

    override fun onSelectedItem(item: D3ViewElement) {
        Log.d("DEBUG", item.name)
        when(item.type) {
            D3ViewType.Class -> {
                replaceFragmentToType(D3ViewType.Build)
            }
            D3ViewType.Build -> {
                startActivity(Intent(baseContext, BuildActivity::class.java))
            }
        }
    }

    override fun onError(message: String) {
    }

    override fun showList(list: List<D3ViewElement>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun replaceFragmentToType(type: D3ViewType) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.list_fragment, ListFragment.newInstance(type))
        ft.commit()
        currentFragmentType = type
    }
}
