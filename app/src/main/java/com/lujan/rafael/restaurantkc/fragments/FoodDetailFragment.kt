package com.lujan.rafael.restaurantkc.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.activities.FoodDetailActivity
import com.lujan.rafael.restaurantkc.model.Allergens
import com.lujan.rafael.restaurantkc.model.Food
import kotlinx.android.synthetic.main.fragment_food_detail.*

class FoodDetailFragment : Fragment() {

    companion object {
        val ARG_FOOD = "ARG_FOOD"

        fun newInstance(food: Food) = FoodDetailFragment().apply {
            arguments = Bundle().apply {
                putSerializable(ARG_FOOD, food)
            }
        }
    }

    interface eventFoodListener {
        fun addNewFoodEvent(newFood: Food)
    }
    var newFoodListener: eventFoodListener? = null

    lateinit var food: Food

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        food = arguments?.getSerializable(ARG_FOOD) as Food
        food_detail_image.setImageResource(food.image)
        food_detail_name.text = food.name
        food_detail_description.text = food.description
        food.allergens.forEach {
            when(it) {
                Allergens.GLUTEN -> image_gluten_allergen.setImageResource(R.drawable.allergen_gluten_yes)
                Allergens.CELERY -> image_celery_allergen.setImageResource(R.drawable.allergen_celery_yes)
                Allergens.MILK -> image_milk_allergen.setImageResource(R.drawable.allergen_milk_yes)
                Allergens.SHELLFISH -> image_shellfish_allergen.setImageResource(R.drawable.allergen_shellfish_yes)
                Allergens.FISH -> image_fish_allergen.setImageResource(R.drawable.allergen_fish_yes)
                Allergens.EGG -> image_egg_allergen.setImageResource(R.drawable.allergen_egg_yes)
            }
        }

        //FloatingButton
        add_button.setOnClickListener {
            FoodDetailActivity.Log.info("Event: New food added")
            food.notes = food_notes.text.toString()
            newFoodListener?.addNewFoodEvent(food)
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
        newFoodListener = null
    }

    fun commonAttach(activity: Activity?) {
        if (activity is FoodDetailFragment.eventFoodListener) {
            newFoodListener = activity
        } else newFoodListener = null
    }
}
