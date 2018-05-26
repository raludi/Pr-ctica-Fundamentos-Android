package com.lujan.rafael.restaurantkc.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.lujan.rafael.restaurantkc.R
import com.lujan.rafael.restaurantkc.ViewHolderFactory
import com.lujan.rafael.restaurantkc.adapters.GenericRecyclerAdapter
import com.lujan.rafael.restaurantkc.fragments.TablesFragment

class TableListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tables_activity)
        if (supportFragmentManager.findFragmentById(R.id.tables_fragment) == null) {
            // Añadiremos el fragment de forma dinámica
            val fragment = TablesFragment.newInstance()

            supportFragmentManager.beginTransaction()
                    .add(R.id.foods_fragment, fragment)
                    .commit()
        }
    }
}
