package com.example.d3companion.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.d3companion.R
import com.example.d3companion.models.D3Item

class ListActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: D3Item?) {
        Log.d("DEBUG", item?.name)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}
