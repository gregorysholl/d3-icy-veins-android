package com.example.d3companion.presenters.list

import android.util.Log
import com.example.d3companion.models.D3Class
import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.services.D3IcyVeinsListener
import com.example.d3companion.services.D3IcyVeinsProvider
import com.example.d3companion.views.list.ListView
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.ref.WeakReference

class ListPresenter(
    view: ListView,
    provider: D3IcyVeinsProvider
) : ListPresenterProvider, D3IcyVeinsListener {

    private var weakView: WeakReference<ListView>? = WeakReference(view)

    private var weakProvider: WeakReference<D3IcyVeinsProvider>? = WeakReference(provider)

    private var list: List<D3Class> = emptyList()

    init {
        weakProvider?.get()?.listener = this
        weakProvider?.get()?.obtainData()
    }

    override fun getClasses() {
        weakView?.get()?.showList(getClassViews())
    }

    override fun getBuilds(className: String) {
        weakView?.get()?.showList(getBuildViews(className))
    }

    override fun onDataRetrieved(data: String) {
        Log.d("DATA", data)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val listType = object : TypeToken<List<D3Class>>() { }.type

        list = gson.fromJson<List<D3Class>>(data, listType)
    }

    override fun onError(message: String) {
        weakView?.get()?.showError()
    }

    private fun getClassViews(): List<D3ViewElement> {
        val views = emptyList<D3ViewElement>().toMutableList()
        for (d3Class in list) {
            views.add(D3ViewElement(d3Class.name, D3ViewType.Class))
        }
        return views
    }

    private fun getBuildViews(className: String): List<D3ViewElement> {
        val views = emptyList<D3ViewElement>().toMutableList()

        try {
            val selectedClass = list.first { it.name == className }
            for (build in selectedClass.builds) {
                views.add(D3ViewElement(build.name, D3ViewType.Build))
            }
        } catch (e: NoSuchElementException) {
            weakView?.get()?.showError()
        }

        return views
    }
}
