package com.example.d3companion.services

import android.content.Context
import com.example.d3companion.R
import java.lang.ref.WeakReference

class FileD3IcyVeinsProvider(
    context: Context?,
    private var listener: D3IcyVeinsListener?
) : D3IcyVeinsProvider {

    private val weakContext: WeakReference<Context?> = WeakReference(context)

    override fun obtainData() {
        if (weakContext.get()?.resources == null) {
            listener?.onError("")
            return
        }

        val inputStream = weakContext.get()?.resources?.openRawResource(R.raw.builds)

        val text = inputStream?.bufferedReader().use { it?.readText() }
        if (text == null) {
            listener?.onError("")
            return
        }

        listener?.onDataRetrieved(text)
    }

    fun setListener(newListener: D3IcyVeinsListener) {
        listener = newListener
    }
}
