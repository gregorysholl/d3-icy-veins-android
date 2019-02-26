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

class ListFragment : Fragment() {

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"
        const val ARG_LIST = "d3-list"

        @JvmStatic
        fun newInstance(list: List<D3ViewElement>) = ListFragment().apply {
            arguments = Bundle().apply {
                putParcelableArrayList(ARG_LIST, ArrayList(list))
            }
        }
    }

    private var columnCount = 1

    private var list: List<D3ViewElement> = emptyList()

    private var listener: Listener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
            list = it.getParcelableArrayList(ARG_LIST) ?: emptyList()
        }
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
                adapter = ViewElementAdapter(list, listener)
            }
        }
        return view
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

    interface Listener {

        fun onSelectedItem(item: D3ViewElement)
    }
}
