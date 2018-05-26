package com.lujan.rafael.restaurantkc.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.fragments.FoodsFragment

class FoodListActivity : AppCompatActivity() {

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_food_list)
        val adapter = object: GenericRecyclerAdapter<Any>(Foods.listFood) {
            override fun clickItem(view: View, itemSelected: Any) {
                val food = itemSelected as Food
                val intent =  FoodDetailActivity.intent(view.context,food)
                startActivity(intent)
            }

            override fun getViewHolder(view: View, viewType: Int): RecyclerView.ViewHolder {
                return ViewHolderFactory.create(view, viewType)
            }

            override fun getLayoutId(): Int {
                return R.layout.content_food_item
            }
        }
        food_list.layoutManager = LinearLayoutManager(this)
        food_list.adapter = adapter
    }*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.foods_activity)
        if (supportFragmentManager.findFragmentById(R.id.foods_fragment) == null) {
            // Añadiremos el fragment de forma dinámica
            val fragment = FoodsFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.foods_fragment, fragment)
                    .commit()
        }
    }

}

