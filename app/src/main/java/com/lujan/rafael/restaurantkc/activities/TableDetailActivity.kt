package com.lujan.rafael.restaurantkc.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.fragments.TableDetailFragment
import com.lujan.rafael.restaurantkc.model.Food
import com.lujan.rafael.restaurantkc.model.Order

class TableDetailActivity : AppCompatActivity(), TableDetailFragment.OnPayedListener{

    companion object {
        val ARG_ORDER = "order_detail"
        fun intent(context: Context, order: Order): Intent {
            val intent = Intent(context, TableDetailActivity::class.java)
            intent.putExtra(ARG_ORDER, order)
            return intent
        }
    }

    lateinit var order: Order

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_detail_activity)
        order = intent.getSerializableExtra(ARG_ORDER) as Order
        if (supportFragmentManager.findFragmentById(R.id.table_detail_fragment) == null) {
            val fragment = TableDetailFragment.newInstance(order)
            supportFragmentManager.beginTransaction()
                    .add(R.id.table_detail_fragment, fragment)
                    .commit()
        }
    }

    override fun OnPayedClickedListener() {
        val returnIntent = Intent()
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

}
