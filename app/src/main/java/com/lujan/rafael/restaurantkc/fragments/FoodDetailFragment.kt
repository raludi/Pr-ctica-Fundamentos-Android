package com.lujan.rafael.restaurantkc.fragments

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_food_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val food = arguments?.getSerializable(ARG_FOOD) as Food
        food_detail_image.setImageResource(food.image)
        food_detail_name.text = food.name
        food_detail_description.text = food.description

    }

}
