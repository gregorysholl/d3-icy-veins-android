package com.example.d3companion.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.d3companion.R
import com.example.d3companion.list.dummy.DummyContent

class ListActivity : AppCompatActivity(), ItemFragment.OnListFragmentInteractionListener {

    override fun onListFragmentInteraction(item: DummyContent.DummyItem?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
    }
}
