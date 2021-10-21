package org.wit.perfectpizza.console.models

data class PizzaPlaceModel (
            var id: Long = 0,
            var name: String = "",
            var location: String = "",
            var review: String = "",
            var choice: String = ""
            //var rating: Int
)