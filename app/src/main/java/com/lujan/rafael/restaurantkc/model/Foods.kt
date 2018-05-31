package com.lujan.rafael.restaurantkc.model

import com.lujan.rafael.restaurantkc.R

object Foods {
      val listFood: List<Food> = listOf(
            Food("Pescado","Pescado a la plancha acompañado de patatas fritas", "", 17.0F, R.drawable.fish,
                    listOf(Allergens.FISH, Allergens.GLUTEN)),
            Food("Ensalada","Ensalada de lechuga, tomate, anchoas, huevo duro, aceitunas y ", "", 7.0F, R.drawable.salad,
                    listOf(Allergens.FISH, Allergens.EGG)),
            Food("Mariscada","Gran festín de marisco y pescado", "", 24.5F, R.drawable.shellfish,
                    listOf(Allergens.SHELLFISH, Allergens.FISH, Allergens.GLUTEN)),
            Food("Arroz","Arroz especiado con toques orientales", "", 6.0F, R.drawable.rice,
                    listOf(Allergens.CELERY, Allergens.GLUTEN)),
            Food("Huevos fritos","Huevos fritos con patatas", "", 5.0F, R.drawable.fry_eggs,
                    listOf(Allergens.EGG)),
            Food("Pasta","Pasta boloseña aromatizada", "", 6.4F, R.drawable.pasta,
                    listOf(Allergens.GLUTEN, Allergens.CELERY)),
            Food("Chocolate","Degustación de diferentes tipos de chocolate", "",3.0F, R.drawable.chocolate,
                    listOf(Allergens.MILK)),
            Food("Fresas","Fresas con nata", "", 2.0F, R.drawable.strawberries,
                    listOf(Allergens.MILK)),
            Food("Lentejas","Lentejas al estilo tradicional como las de la abuela", "", 9.99F, R.drawable.lentils,
                    listOf(Allergens.GLUTEN)),
            Food("Hamburguesa","Blue fish with fries", "", 10.0F, R.drawable.hamburger,
                    listOf(Allergens.GLUTEN, Allergens.CELERY)),
            Food("Cannelloni","Canelones rellenos de atún, huevo, carne y jamón", "", 12.0F, R.drawable.cannelloni,
                    listOf(Allergens.MILK, Allergens.CELERY, Allergens.FISH, Allergens.EGG)),
              Food("Solomillo","Solomillo acompañado de salsa de oporto y patatas", "", 15.8F, R.drawable.steak,
                      listOf(Allergens.GLUTEN))
    )
    val count
        get() = listFood.size
    fun getFood(index: Int) = listFood[index]
    fun toArray() = listFood.toTypedArray()
}