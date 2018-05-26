package com.lujan.rafael.restaurantkc.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.ViewHolderFactory
import com.lujan.rafael.restaurantkc.activities.FoodDetailActivity
import com.lujan.rafael.restaurantkc.activities.FoodListActivity
import com.lujan.rafael.restaurantkc.adapters.GenericRecyclerAdapter
import com.lujan.rafael.restaurantkc.model.Food
import com.lujan.rafael.restaurantkc.model.Foods
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodsFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment = FoodsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = RecyclerAdapter()
        food_list.layoutManager = LinearLayoutManager(view.context)
        food_list.adapter = adapter
    }

    inner class RecyclerAdapter:GenericRecyclerAdapter<Any>(Foods.listFood) {
        override fun clickItem(view: View, itemSelected: Any) {
            val food = itemSelected as Food
            val intent =  FoodDetailActivity.intent(view.context,food)
            startActivity(intent)
        }

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolderFactory.create(view, viewType)
        }

        override fun getLayoutId(): Int {
            return R.layout.content_food_item
        }
    }

}
