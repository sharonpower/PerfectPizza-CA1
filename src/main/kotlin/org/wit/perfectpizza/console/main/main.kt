package org.wit.perfectpizza.console.main

import mu.KotlinLogging
import org.wit.perfectpizza.console.controllers.PizzaPlaceController
import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.models.PizzaPlaceModel
import org.wit.perfectpizza.console.views.PizzaPlaceView
import java.awt.SystemColor.menu

private val logger = KotlinLogging.logger {}

val pizzaPlaces = PizzaPlaceMemStore()
val pizzaPlaceView = PizzaPlaceView()


fun main(args: Array<String>){
    PizzaPlaceController().start()
}

fun addPizzaPlace(){
    var onePizzaPlace = PizzaPlaceModel()

    if (pizzaPlaceView.addPizzaPlaceData(onePizzaPlace))
        pizzaPlaces.create(onePizzaPlace)
    else
        logger.info("Pizza Place review not added.")
    
}

fun updatePizzaPlace(){
    pizzaPlaceView.listAllPizzaPlaces(pizzaPlaces)
    var searchId = pizzaPlaceView.getId()
    val onePizzaPlace = search(searchId)

    if(onePizzaPlace != null){
        if(pizzaPlaceView.updatePizzaPlaceData(onePizzaPlace)) {
            pizzaPlaces.update(onePizzaPlace)
            pizzaPlaceView.showPizzaPlace(onePizzaPlace)
            logger.info("Pizza Place review update : [ $onePizzaPlace ]")
        }
        else
            logger.info("Pizza Place review not updated ...")
    }
    else
        logger.info("Pizza Place review not updated ...")
}

fun searchPizzaPlaces() {
    val onePizzaPlace = search(pizzaPlaceView.getId())!!
    pizzaPlaceView.showPizzaPlace(onePizzaPlace)
}

fun search(id: Long) : PizzaPlaceModel? {
    var foundPizzaPlace = pizzaPlaces.findOne(id)
    return foundPizzaPlace
}

fun prepopulatedData() {
    pizzaPlaces.create(PizzaPlaceModel(1, "Robertos", "Carrick-On-Suir"))
    pizzaPlaces.create(PizzaPlaceModel(2, "Jimmys", "Portlaw"))
    pizzaPlaces.create(PizzaPlaceModel(3, "Dominoes", "Dungarvan"))
}
