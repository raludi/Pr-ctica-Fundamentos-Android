package com.lujan.rafael.restaurantkc.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.*
import android.widget.ArrayAdapter
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.activities.FoodListActivity
import com.lujan.rafael.restaurantkc.model.Order
import kotlinx.android.synthetic.main.fragment_table_detail.*

class TableDetailFragment: Fragment() {

    companion object {
        val ARG_DETAIL_ORDER = "arg_detail_order"
        fun newInstance(order: Order): Fragment = TableDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_DETAIL_ORDER, order)
            }
        }
    }

    interface OnEventOrderListener {
        fun OnPayedClickedListener(orderPayed: Order)
        fun OnBackClickedListener(orderBack: Order)
    }

    lateinit var order: Order
    var eventOrderListener: OnEventOrderListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_table_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        order = arguments?.getSerializable(TableDetailFragment.ARG_DETAIL_ORDER) as Order
        total_account.text = getString(R.string.total_bill, order.calculateBill())
        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, order.dishes)
        dishes_list.adapter = adapter
        pay_account_btn.setOnClickListener {
            order.dishes = mutableListOf()
            eventOrderListener?.OnPayedClickedListener(order)
        }


        reset_order_btn.setOnClickListener{
            total_account.text = getString(R.string.total_bill, 0F)
            val oldUnit = order.dishes
            order.dishes = mutableListOf()
            val adapterReset = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, order.dishes)
            dishes_list.adapter = adapterReset

            Snackbar.make(view, "Lista de pedidos borrada", Snackbar.LENGTH_LONG)
                    .setAction("Deshacer", {
                        order.dishes = oldUnit
                        val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, order.dishes)
                        dishes_list.adapter = adapter
                    })
                    .show()
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
        eventOrderListener = null
    }

    fun commonAttach(activity: Activity?) {
        if (activity is TableDetailFragment.OnEventOrderListener) {
            eventOrderListener = activity
        } else eventOrderListener = null
    }



    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            val orderReturned = data?.getSerializableExtra(FoodListActivity.RETURNED_LIST) as Order
            order.dishes = orderReturned.dishes
            val adapter = ArrayAdapter(view?.context, android.R.layout.simple_list_item_1, order.dishes)
            adapter.notifyDataSetChanged()
            dishes_list.adapter = adapter
            val total = order.calculateBill()
            total_account.text = getString(R.string.total_bill, total)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.option_add_new_food-> {
                val intent = FoodListActivity.intent(view!!.context, order)
                startActivityForResult(intent,1)
                true
            }
            android.R.id.home -> {
                eventOrderListener?.OnBackClickedListener(order)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}