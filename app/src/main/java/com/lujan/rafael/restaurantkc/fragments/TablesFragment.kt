package com.lujan.rafael.restaurantkc.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.ViewHolderFactory
import com.lujan.rafael.restaurantkc.activities.TableDetailActivity
import com.lujan.rafael.restaurantkc.adapters.GenericRecyclerAdapter
import com.lujan.rafael.restaurantkc.model.Food
import com.lujan.rafael.restaurantkc.model.Order
import kotlinx.android.synthetic.main.fragment_table_list.*

class TablesFragment: Fragment() {
    companion object {
        fun newInstance(): Fragment = TablesFragment()
    }

    val tables: List<String> = listOf(
            "Mesa 1", "Mesa 2", "Mesa 3", "Mesa 4",
            "Mesa 5", "Mesa 6", "Mesa 7", "Mesa 8",
            "Mesa 9", "Mesa 10"
    )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = object: GenericRecyclerAdapter<Any>(tables) {
            override fun clickItem(view: View, itemSelected: Any) {
                val nameTable = itemSelected as String
                val emptyListFoods = mutableListOf<Food>()
                val order = Order(nameTable, 0F, emptyListFoods)
                val intent = TableDetailActivity.intent(view.context, order)
                startActivityForResult(intent,1)
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.create(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.content_table_item
            }
        }
        table_list.layoutManager = LinearLayoutManager(view.context)
        //table_list.layoutManager = GridLayoutManager(view.context,2)
        table_list.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Activity.RESULT_OK) {
           println("Mesa libre")
        }
    }

}