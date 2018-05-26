package com.lujan.rafael.restaurantkc.model

import java.io.Serializable
//añadir additional
data class Food(val name: String, val description: String, val price: Float, val image: Int, val allergens: String) : Serializable