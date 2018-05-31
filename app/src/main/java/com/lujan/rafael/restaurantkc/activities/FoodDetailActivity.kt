package com.lujan.rafael.restaurantkc.activities

import android.app.Activity
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

class FoodDetailActivity : AppCompatActivity(), FoodDetailFragment.eventFoodListener {


    companion object {
        val ARG_FOOD = "food_info"
        val ARG_SELECTED = "food_selected"
        val Log = Logger.getLogger(FoodDetailActivity::class.java.name)

        fun intent(context: Context, food: Food): Intent {
            val intent = Intent(context, FoodDetailActivity::class.java)
            intent.putExtra(ARG_FOOD, food)
            return intent
        }
    }

    lateinit var food: Food

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.food_detail_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //Obtenemos datos
        food = intent.getSerializableExtra(ARG_FOOD) as Food
        //Creamos fragment
        if (supportFragmentManager.findFragmentById(R.id.food_detail_fragment) == null) {
            val fragment = FoodDetailFragment.newInstance(food)

            supportFragmentManager.beginTransaction()
                    .add(R.id.food_detail_fragment, fragment)
                    .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        android.R.id.home -> {
            val returnIntent = Intent()
            returnIntent.putExtra(ARG_SELECTED, food)
            setResult(Activity.RESULT_CANCELED, returnIntent)
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override fun addNewFoodEvent(newFood: Food) {
        val returnIntent = Intent()
        returnIntent.putExtra(FoodDetailActivity.ARG_SELECTED, newFood)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }
}
