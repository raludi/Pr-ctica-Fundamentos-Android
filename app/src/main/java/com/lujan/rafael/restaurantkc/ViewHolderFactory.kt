package com.lujan.rafael.restaurantkc

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.lujan.rafael.restaurantkc.adapters.GenericRecyclerAdapter
import com.lujan.rafael.restaurantkc.model.Food

object ViewHolderFactory {
    fun create(view: View, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            R.layout.content_food_item -> FoodViewHolder(view)
            R.layout.content_table_item -> TableViewHolder(view)
            else -> {
                FoodViewHolder(view)
            }
        }
    }

    class FoodViewHolder(view: View): RecyclerView.ViewHolder(view), GenericRecyclerAdapter.Binder<Food> {
        var name: TextView = view.findViewById(R.id.txt_food_name)
        var description: TextView = view.findViewById(R.id.txt_food_description)
        var price: TextView = view.findViewById(R.id.txt_food_price)
        var image: ImageView = view.findViewById(R.id.food_image)
        override fun bind(data: Food) {
            name.text = data.name
            description.text = data.description
            price.text = data.price.toString() + "€"
            image.setImageResource(data.image)
        }
    }

    class TableViewHolder(view: View): RecyclerView.ViewHolder(view), GenericRecyclerAdapter.Binder<Int> {
        var name: TextView = view.findViewById(R.id.table_text)
        var image: ImageView = view.findViewById(R.id.table_image)

        override fun bind(data: Int) {
            name.text = "Mesa " + data.toString()
            image.setImageResource(R.drawable.table)
        }
    }

}