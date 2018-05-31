package com.lujan.rafael.restaurantkc.model

import java.io.Serializable
//añadir additional
data class Food(val name: String, val description: String, var notes: String, val price: Float, val image: Int, val allergens: List<Allergens>) : Serializable {
    override fun toString(): String = name
}