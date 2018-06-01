package com.lujan.rafael.restaurantkc.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.fragments.TableDetailFragment
import com.lujan.rafael.restaurantkc.model.Order

class TableDetailActivity : AppCompatActivity(), TableDetailFragment.OnEventOrderListener{

    companion object {
        val ARG_ORDER = "order_detail"
        val ARG_ORDER_PAYED = "order_payed"
        val ARG_ORDER_BACK = "order_change"
        fun intent(context: Context, order: Order): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(ARG_ORDER, order)
            return intent
        }
    }

    lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setContentView(R.layout.table_detail_activity)
        order = intent.getSerializableExtra(ARG_ORDER) as Order
        if (supportFragmentManager.findFragmentById(R.id.table_detail_fragment) == null) {
            val fragment = TableDetailFragment.newInstance(order)
            supportFragmentManager.beginTransaction()
                    .add(R.id.table_detail_fragment, fragment)
                    .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.add_food_menu, menu)
        return true
    }

    override fun OnPayedClickedListener(orderPayed: Order) {
        val returnIntent = Intent()
        returnIntent.putExtra(ARG_ORDER_PAYED, orderPayed)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun OnBackClickedListener(orderBack: Order) {
        val returnIntent = Intent()
        returnIntent.putExtra(ARG_ORDER_BACK, orderBack)
        setResult(Activity.RESULT_CANCELED, returnIntent)
        finish()
    }

}
