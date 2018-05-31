package com.lujan.rafael.restaurantkc.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.fragments.FoodsFragment
import com.lujan.rafael.restaurantkc.model.Food
import com.lujan.rafael.restaurantkc.model.Order

class FoodListActivity : AppCompatActivity(), FoodsFragment.notifyChangeListener {


    companion object {
        val ARG_ORDER_LIST = "order_list"
        val RETURNED_LIST = "order_return"
        fun intent(context: Context, order: Order): Intent {
            val intent = Intent(context, FoodListActivity::class.java)
            intent.putExtra(ARG_ORDER_LIST, order)
            return intent
        }
    }

    lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foods_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        order = intent.getSerializableExtra(ARG_ORDER_LIST) as Order
        if (supportFragmentManager.findFragmentById(R.id.foods_fragment) == null) {
            val fragment = FoodsFragment.newInstance()
            supportFragmentManager.beginTransaction()
                    .add(R.id.foods_fragment, fragment)
                    .commit()
        }
    }

    override fun notifyListener(food: Food) {
        order.addNewDish(food)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            val returnIntent = Intent()
            returnIntent.putExtra(RETURNED_LIST, order)
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}

