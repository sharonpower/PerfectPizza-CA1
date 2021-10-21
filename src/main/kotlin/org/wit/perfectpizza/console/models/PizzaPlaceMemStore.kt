package org.wit.perfectpizza.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class PizzaPlaceMemStore : PizzaPlaceStore {

    val pizzaPlaces = ArrayList<PizzaPlaceModel>()

    override fun findAll(): List<PizzaPlaceModel> {
        return pizzaPlaces
    }

    override fun findOne(id: Long) : PizzaPlaceModel? {
        var foundPizzaPlace: PizzaPlaceModel? = pizzaPlaces.find { p -> p.id == id }
        return foundPizzaPlace
    }

    override fun create(pizzaPlace: PizzaPlaceModel) {
        pizzaPlace.id = getId()
        pizzaPlaces.add(pizzaPlace)
        logAll()
    }

    override fun update(pizzaPlace: PizzaPlaceModel) {
        var foundPizzaPlace = findOne(pizzaPlace.id!!)
        if (foundPizzaPlace != null) {
            foundPizzaPlace.name = pizzaPlace.name
            foundPizzaPlace.location = pizzaPlace.location
            foundPizzaPlace.choice = pizzaPlace.choice
            foundPizzaPlace.review = pizzaPlace.review
        }
    }

    override fun delete(pizzaPlace: PizzaPlaceModel) {
        pizzaPlaces.remove(pizzaPlace)
    }

    internal fun logAll() {
        pizzaPlaces.forEach { logger.info("${it}") }
    }
}