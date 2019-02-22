package com.example.d3companion.views.list

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.d3companion.R

import com.example.d3companion.models.D3ViewElement
import com.example.d3companion.models.D3ViewType
import com.example.d3companion.presenters.list.ListPresenter
import com.example.d3companion.presenters.list.ListPresenterProvider
import com.example.d3companion.services.FileD3IcyVeinsProvider

class ListFragment : Fragment(), ListView {

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_TYPE = "type"
        const val ARG_BUILD_NAME = "name"

        @JvmStatic
        fun newInstance(type: D3ViewType, name: String? = null) = ListFragment().apply {
            arguments = Bundle().apply {
                putString(ARG_TYPE, type.name)
                putString(ARG_BUILD_NAME, name)
            }
        }
    }

    private var columnCount = 1

    private var type: D3ViewType = D3ViewType.Class
    private var name: String? = null

    private var presenter: ListPresenterProvider? = null

    private var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            type = D3ViewType.valueOf(it.getString(ARG_TYPE) ?: "Class")
            name = it.getString(ARG_BUILD_NAME)
        }

        presenter = ListPresenter(this, FileD3IcyVeinsProvider(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_item_list, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
            }
        }
        return view
    }

    override fun onStart() {
        super.onStart()

        presenter?.getItems(type)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Listener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement Listener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    override fun showList(list: List<D3ViewElement>) {
        val recyclerView = view
        if (recyclerView is RecyclerView) {
            recyclerView.adapter = ViewElementAdapter(list, listener)
        }
    }

    override fun showError() {
    }

    interface Listener {

        fun onSelectedItem(item: D3ViewElement)

        fun onError(message: String)
    }
}
