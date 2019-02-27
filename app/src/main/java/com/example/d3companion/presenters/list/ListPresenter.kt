package com.example.d3companion.presenters.list

import android.util.Log
import com.example.d3companion.models.D3Class
import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.services.D3IcyVeinsContract
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.lang.ref.WeakReference

class ListPresenter(
    view: ListContract.View,
    provider: D3IcyVeinsContract.Provider
) : ListContract.Presenter, D3IcyVeinsContract.Listener {

    private var weakView: WeakReference<ListContract.View>? = WeakReference(view)

    private var weakProvider: WeakReference<D3IcyVeinsContract.Provider>? = WeakReference(provider)

    private var list: List<D3Class> = emptyList()

    private var selectedClassName: String? = null

    init {
        weakProvider?.get()?.listener = this
    }

    override fun getClasses() {
        selectedClassName = null

        showList()
    }

    override fun getBuilds(className: String) {
        selectedClassName = className

        showList()
    }

    override fun getBuild(name: String) {
        if (selectedClassName == null) {
            weakView?.get()?.showError()
            return
        }

        val build = list.firstOrNull { it.name == selectedClassName }?.builds?.firstOrNull { it.name == name }
        if (build == null) {
            weakView?.get()?.showError()
            return
        }

        weakView?.get()?.showBuildActivity(build)
    }

    override fun onDataRetrieved(data: String) {
        Log.d("DATA", data)

        val gson = GsonBuilder().setPrettyPrinting().create()
        val listType = object : TypeToken<List<D3Class>>() { }.type

        list = gson.fromJson<List<D3Class>>(data, listType)

        showList()
    }

    override fun onError(message: String) {
        weakView?.get()?.showError()
    }

    private fun showList() {
        if (list.isEmpty()) {
            weakProvider?.get()?.obtainData()
            return
        }

        val views = if (selectedClassName != null) {
            getBuildViews(selectedClassName)
        } else {
            getClassViews()
        }
        weakView?.get()?.showList(views)
    }

    private fun obtainData() {
        if (!list.isEmpty()) {
            return
        }
        weakProvider?.get()?.obtainData()
    }

    private fun getClassViews(): List<D3ViewElement> {
        val views = emptyList<D3ViewElement>().toMutableList()
        for (d3Class in list) {
            views.add(D3ViewElement(d3Class.name, D3ViewType.Class))
        }
        return views
    }

    private fun getBuildViews(className: String?): List<D3ViewElement> {
        val views = emptyList<D3ViewElement>().toMutableList()
        if (className == null) {
            return views
        }

        val selectedClass = list.firstOrNull { it.name == className } ?: return views
        for (build in selectedClass.builds) {
            views.add(D3ViewElement(build.name, D3ViewType.Build))
        }

        return views
    }
}
