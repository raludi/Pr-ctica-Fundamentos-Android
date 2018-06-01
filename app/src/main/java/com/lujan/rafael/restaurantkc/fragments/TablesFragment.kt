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

    var tableList: MutableList<Order> = mutableListOf(
            Order(1, mutableListOf<Food>()),
            Order(2, mutableListOf<Food>()),
            Order(3, mutableListOf<Food>()),
            Order(4, mutableListOf<Food>()),
            Order(5, mutableListOf<Food>()),
            Order(6, mutableListOf<Food>()),
            Order(7, mutableListOf<Food>()),
            Order(8, mutableListOf<Food>()),
            Order(9, mutableListOf<Food>()),
            Order(10, mutableListOf<Food>())
    )

    val tables: List<Int> = listOf(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10
     )

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = object: GenericRecyclerAdapter<Any>(tables) {
            override fun clickItem(view: View, itemSelected: Any) {
                val numberTable = itemSelected as Int
                val order = tableList[numberTable - 1]
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
        val orderReturned: Order
        when(resultCode){
            Activity.RESULT_OK -> {
                orderReturned = data?.getSerializableExtra(TableDetailActivity.ARG_ORDER_PAYED) as Order
                tableList[orderReturned.table - 1] = orderReturned
            }
            Activity.RESULT_CANCELED -> {
                orderReturned = data?.getSerializableExtra(TableDetailActivity.ARG_ORDER_BACK) as Order
                tableList[orderReturned.table - 1] = orderReturned
            }
        }
    }

}

