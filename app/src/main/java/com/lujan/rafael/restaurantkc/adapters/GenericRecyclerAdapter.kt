package com.lujan.rafael.restaurantkc.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.model.Food
import kotlinx.android.synthetic.main.content_food_item.view.*

/*class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val nameFood = view.txt_food_name
    val descriptionFood = view.txt_food_description
    val priceFood = view.txt_food_price
    val imageFood = view.food_image
}

class GenericRecyclerAdapter(val elements: Array<Food>, val context: Context): RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameFood.text = elements.get(position).name
        holder.descriptionFood.text = elements.get(position).description
        holder.priceFood.text = elements.get(position).price.toString()
        holder.imageFood.setImageResource(elements.get(position).image)
    }

    override fun getItemCount() = elements.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ViewHolder(LayoutInflater.from(context).inflate(R.layout.food_list_item, parent, false))
        return view
    }
}
*/
abstract class GenericRecyclerAdapter<T> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var listItems: List<T>
    var onClickListener: View.OnClickListener? = null

    constructor(listItems: List<T>) {
        this.listItems = listItems
    }

    constructor() {
        listItems = emptyList()
    }

    abstract fun getLayoutId(): Int
    abstract fun getViewHolder(view: View, viewType: Int):RecyclerView.ViewHolder
    internal interface Binder<T> {
        fun bind(data: T)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view = getViewHolder(LayoutInflater.from(parent.context)
                .inflate(viewType, parent, false)
                , viewType)
        view
        return view
    }

    abstract fun clickItem(view: View, itemSelected: T)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as Binder<T>).bind(listItems[position])
        holder.itemView.setOnClickListener {
            clickItem(holder.itemView, listItems[position])
        }
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutId()
    }

}

