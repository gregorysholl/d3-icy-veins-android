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
import java.util.ArrayList

class ListPresenter(
    view: ListView,
    provider: D3IcyVeinsProvider
) : ListPresenterProvider, D3IcyVeinsListener {

    private var weakView: WeakReference<ListView>? = WeakReference(view)

    private var weakProvider: WeakReference<D3IcyVeinsProvider>? = WeakReference(provider)

    private val items: MutableList<D3ViewElement> = ArrayList()

    init {
        weakProvider?.get()?.listener = this
        weakProvider?.get()?.obtainData()
    }

    override fun getItems(type: D3ViewType) {
        weakView?.get()?.showList(items.filter { it.type == type })
    }

    override fun onDataRetrieved(data: String) {
        Log.d("DATA", data)

        val gson = GsonBuilder().setPrettyPrinting().create()

        val listType = object : TypeToken<List<D3Class>>() { }.type
        val list = gson.fromJson<List<D3Class>>(data, listType)

        Log.d("DATA", "Retrieved list length = ${list.count()}")

        createClasses(list)
    }

    override fun onError(message: String) {
        Log.d("ERROR", message)
    }

    private fun addItem(item: D3ViewElement) {
        items.add(item)
    }

    private fun createClasses(list: List<D3Class>) {
        for (d3Class in list) {
            items.add(D3ViewElement(d3Class.name, D3ViewType.Class))
        }
    }

    private fun createBuilds() {
        addItem(D3ViewElement("Build 1", D3ViewType.Build))
        addItem(D3ViewElement("Build 2", D3ViewType.Build))
        addItem(D3ViewElement("Build 3", D3ViewType.Build))
        addItem(D3ViewElement("Build 4", D3ViewType.Build))
    }
}
