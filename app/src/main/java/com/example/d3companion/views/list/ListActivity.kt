package com.example.d3companion.views.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d3companion.R
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3Type

class ListActivity : AppCompatActivity(), ItemFragment.Listener {

    private var currentFragmentType: D3Type = D3Type.Class

    override fun onListFragmentInteraction(item: D3Item) {
        Log.d("DEBUG", item.name)
        when(item.type) {
            D3Type.Class -> {
                replaceFragmentToType(D3Type.Build)
            }
            D3Type.Build -> {
                //TODO
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        replaceFragmentToType(D3Type.Class)
    }

    private fun replaceFragmentToType(type: D3Type) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.list_fragment, ItemFragment.newInstance(type))
        transaction.commit()
        currentFragmentType = type
    }

    override fun onBackPressed() {
        when(currentFragmentType) {
            D3Type.Class -> super.onBackPressed()
            D3Type.Build -> replaceFragmentToType(D3Type.Class)
        }
    }
}
