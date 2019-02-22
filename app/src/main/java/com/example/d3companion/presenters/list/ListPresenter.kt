package com.example.d3companion.presenters.list

import android.util.Log
import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.services.D3IcyVeinsListener
import com.example.d3companion.services.D3IcyVeinsProvider
import com.example.d3companion.views.list.ListView
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
        createBuilds()
        createClasses()
        weakProvider?.get()?.listener = this
        weakProvider?.get()?.obtainData()
    }

    override fun getItems(type: D3ViewType) {
        weakView?.get()?.showList(items.filter { it.type == type })
    }

    override fun onDataRetrieved(data: String) {
        Log.d("DATA", data)
    }

    override fun onError(message: String) {
        Log.d("ERROR", message)
    }

    private fun addItem(item: D3ViewElement) {
        items.add(item)
    }

    private fun createClasses() {
        addItem(D3ViewElement("Wizard", D3ViewType.Class))
        addItem(D3ViewElement("Barbarian", D3ViewType.Class))
        addItem(D3ViewElement("Monk", D3ViewType.Class))
        addItem(D3ViewElement("Witch Doctor", D3ViewType.Class))
        addItem(D3ViewElement("Demon Hunter", D3ViewType.Class))
        addItem(D3ViewElement("Necromancer", D3ViewType.Class))
    }

    private fun createBuilds() {
        addItem(D3ViewElement("Build 1", D3ViewType.Build))
        addItem(D3ViewElement("Build 2", D3ViewType.Build))
        addItem(D3ViewElement("Build 3", D3ViewType.Build))
        addItem(D3ViewElement("Build 4", D3ViewType.Build))
    }
}
