package com.lujan.rafael.restaurantkc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.ViewHolderFactory
import com.lujan.rafael.restaurantkc.adapters.GenericRecyclerAdapter
import kotlinx.android.synthetic.main.fragment_table_list.*

class TablesFragment: Fragment() {
    companion object {
        fun newInstance(): Fragment = TablesFragment()
    }

    val tables: List<String> = listOf(
            "Table 1", "Table 2", "Table 3", "Table 4",
            "Table 5", "Table 6", "Table 7", "Table 8",
            "Table 9", "Table 10"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = object: GenericRecyclerAdapter<Any>(tables) {
            override fun clickItem(view: View, itemSelected: Any) {
                val table = itemSelected as String
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.create(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.content_table_item
            }

        }

        table_list.layoutManager = GridLayoutManager(view.context,2)
        table_list.adapter = adapter
    }
}