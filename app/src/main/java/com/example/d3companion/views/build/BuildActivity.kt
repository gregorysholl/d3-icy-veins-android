package com.example.d3companion.views.build

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.d3companion.R

class BuildActivity : AppCompatActivity() {

    companion object {
        const val ARG_BUILD = "build-activity-build"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_build)
    }
}
