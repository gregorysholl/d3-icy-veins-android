package com.example.d3companion.views.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d3companion.R
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3Type
import com.example.d3companion.views.build.BuildActivity

class ListActivity : AppCompatActivity(), ListFragment.Listener {

    private var currentFragmentType: D3Type = D3Type.Class

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        replaceFragmentToType(D3Type.Class)
    }

    override fun onBackPressed() {
        when(currentFragmentType) {
            D3Type.Class -> super.onBackPressed()
            D3Type.Build -> replaceFragmentToType(D3Type.Class)
        }
    }

    private fun replaceFragmentToType(type: D3Type) {
        val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.list_fragment, ListFragment.newInstance(type))
        ft.commit()
        currentFragmentType = type
    }

    override fun onSelectedItem(item: D3Item) {
        Log.d("DEBUG", item.name)
        when(item.type) {
            D3Type.Class -> {
                replaceFragmentToType(D3Type.Build)
            }
            D3Type.Build -> {
                startActivity(Intent(baseContext, BuildActivity::class.java))
            }
        }
    }

    override fun onError(message: String) {
    }
}
