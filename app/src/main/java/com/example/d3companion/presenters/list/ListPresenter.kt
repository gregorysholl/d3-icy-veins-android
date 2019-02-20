package com.example.d3companion.presenters.list

import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3Type
import com.example.d3companion.views.list.ListView
import java.lang.ref.WeakReference
import java.util.ArrayList

class ListPresenter(view: ListView) : ListPresenterProvider {

    private var weakView: WeakReference<ListView>? = null

    private val items: MutableList<D3Item> = ArrayList()

    init {
        weakView = WeakReference(view)
        createBuilds()
        createClasses()
    }

    override fun getItems(type: D3Type) {
        weakView?.get()?.showList(items.filter { it.type == type })
    }

    private fun addItem(item: D3Item) {
        items.add(item)
    }

    private fun createClasses() {
        addItem(D3Item("Wizard", D3Type.Class))
        addItem(D3Item("Barbarian", D3Type.Class))
        addItem(D3Item("Monk", D3Type.Class))
        addItem(D3Item("Witch Doctor", D3Type.Class))
        addItem(D3Item("Demon Hunter", D3Type.Class))
        addItem(D3Item("Necromancer", D3Type.Class))
    }

    private fun createBuilds() {
        addItem(D3Item("Build 1", D3Type.Build))
        addItem(D3Item("Build 2", D3Type.Build))
        addItem(D3Item("Build 3", D3Type.Build))
        addItem(D3Item("Build 4", D3Type.Build))
    }
}