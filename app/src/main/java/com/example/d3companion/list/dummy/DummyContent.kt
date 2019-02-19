package com.example.d3companion.list.dummy

import com.example.d3companion.models.D3Class
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
    val ITEMS: MutableList<D3Class> = ArrayList()

    init {
        addItem(D3Class("Wizard"))
        addItem(D3Class("Barbarian"))
        addItem(D3Class("Monk"))
        addItem(D3Class("Witch Doctor"))
        addItem(D3Class("Demon Hunter"))
        addItem(D3Class("Necromancer"))
    }

    private fun addItem(item: D3Class) {
        ITEMS.add(item)
    }
}
