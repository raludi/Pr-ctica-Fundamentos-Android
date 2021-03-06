package com.lujan.rafael.restaurantkc.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import com.lujan.rafael.restaurantkc.model.Order
import kotlinx.android.synthetic.main.fragment_food_list.*


class FoodsFragment : Fragment() {

    companion object {
        fun newInstance(): Fragment = FoodsFragment()
    }

    interface notifyChangeListener {
        fun notifyListener(food: Food)
    }

    var didOrderChange: notifyChangeListener? = null

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
            startActivityForResult(intent,1)
        }

        override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
            return ViewHolderFactory.create(view, viewType)
        }

        override fun getLayoutId(): Int {
            return R.layout.content_food_item
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val food = data?.getSerializableExtra(FoodDetailActivity.ARG_SELECTED) as Food
            didOrderChange?.notifyListener(food)
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonAttach(context as? Activity)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonAttach(activity)
    }

    override fun onDetach() {
        super.onDetach()
        didOrderChange = null
    }

    fun commonAttach(activity: Activity?) {
        if (activity is FoodsFragment.notifyChangeListener) {
            didOrderChange = activity
        } else didOrderChange = null
    }

}
