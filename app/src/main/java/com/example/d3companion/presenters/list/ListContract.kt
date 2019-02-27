package com.example.d3companion.presenters.list

import com.example.d3companion.models.D3Build
import com.example.d3companion.models.D3ViewElement

interface ListContract {

    interface Presenter {

        fun getClasses()

        fun getBuilds(className: String)

        fun getBuild(name: String)
    }

    interface View {

        fun showList(list: List<D3ViewElement>)

        fun showBuildActivity(build: D3Build)

        fun showError()
    }
}
