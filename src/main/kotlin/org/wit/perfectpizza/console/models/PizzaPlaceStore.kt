package org.wit.perfectpizza.console.models

interface PizzaPlaceStore {
        fun findAll(): List<PizzaPlaceModel>
        fun findOne(id: Long): PizzaPlaceModel?
        fun create(pizzaPlace: PizzaPlaceModel)
        fun update(pizzaPlace: PizzaPlaceModel)
        fun delete(pizzaPlace: PizzaPlaceModel)
}