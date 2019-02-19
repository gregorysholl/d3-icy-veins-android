package com.example.d3companion.list.dummy

import com.example.d3companion.models.D3Class
import com.example.d3companion.models.D3Item
import com.example.d3companion.models.D3Type
import java.util.ArrayList

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 *
 * TODO: Replace all uses of this class before publishing your app.
 */
object DummyContent {

    /**
     * An array of sample (dummy) items.
     */
    val ITEMS: MutableList<D3Item> = ArrayList()

    init {
        createClasses()
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

    private fun addItem(item: D3Item) {
        ITEMS.add(item)
    }
}
