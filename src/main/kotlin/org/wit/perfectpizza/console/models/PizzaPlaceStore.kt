package org.wit.perfectpizza.console.models

interface PizzaPlaceStore {
        fun findAll(): List<PizzaPlaceModel>
        fun findOne(id: Long): PizzaPlaceModel?
        fun create(placemark: PizzaPlaceModel)
        fun update(placemark: PizzaPlaceModel)
}