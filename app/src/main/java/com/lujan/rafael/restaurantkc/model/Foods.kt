package com.lujan.rafael.restaurantkc.model

import com.lujan.rafael.restaurantkc.R

object Foods {
      val listFood: List<Food> = listOf(
            Food("Fish","Blue fish with fries",17.0F, R.drawable.fish,"None"),
            Food("Salad","Blue fish with fries",7.0F, R.drawable.salad,"None"),
            Food("ShellFish","Blue fish with fries",24.5F, R.drawable.shellfish,"None"),
            Food("Rice","Blue fish with fries",6.0F, R.drawable.rice,"None"),
            Food("Fry Eggs","Blue fish with fries",5.0F, R.drawable.fry_eggs,"None"),
            Food("Pasta","Blue fish with fries",6.4F, R.drawable.pasta,"None"),
            Food("Chocolate","Blue fish with fries",3.0F, R.drawable.chocolate,"None"),
            Food("Strawberries","Blue fish with fries",2.0F, R.drawable.strawberries,"None"),
            Food("Lentils","Blue fish with fries",9.99F, R.drawable.lentils,"None"),
            Food("Hamburger","Blue fish with fries",10.0F, R.drawable.hamburger,"None"),
            Food("Cannelloni","Blue fish with fries",12.0F, R.drawable.cannelloni,"None"),
              Food("Steak","Blue fish with fries",15.8F, R.drawable.steak,"None")
    )
    val count
        get() = listFood.size
    fun getFood(index: Int) = listFood[index]
    fun toArray() = listFood.toTypedArray()
}