package com.lujan.rafael.restaurantkc.model

import java.io.Serializable

class Order(val table: String, var bill: Float, var dishes: MutableList<Food>): Serializable {

    fun addNewDish(food: Food) {
        this.dishes.add(food)
    }

    fun calculateBill(): Float {
        var total = 0F
        dishes.forEach {
            total += it.price
        }
        return total
    }

}