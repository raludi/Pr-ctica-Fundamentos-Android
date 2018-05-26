package com.lujan.rafael.restaurantkc.activities

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.fragments.FoodDetailFragment
import com.lujan.rafael.restaurantkc.model.Food
import kotlinx.android.synthetic.main.food_detail_activity.*
import java.util.logging.Logger

class FoodDetailActivity : AppCompatActivity() {
    companion object {
        val EXTRA_FOOD = "food_selected"

        val Log = Logger.getLogger(FoodDetailActivity::class.java.name)

        fun intent(context: Context, food: Food): Intent {
            val intent = Intent(context, FoodDetailActivity::class.java)
            intent.putExtra(EXTRA_FOOD, food)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Obtenemos datos
        val food = intent.getSerializableExtra(EXTRA_FOOD) as Food
        //Creamos fragment
        if (supportFragmentManager.findFragmentById(R.id.food_detail_fragment) == null) {
            val fragment = FoodDetailFragment.newInstance(food)

            supportFragmentManager.beginTransaction()
                    .add(R.id.food_detail_fragment, fragment)
                    .commit()
        }
        //FloatingButton
        add_button.setOnClickListener {
            Log.info("Event: New food added")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

}
