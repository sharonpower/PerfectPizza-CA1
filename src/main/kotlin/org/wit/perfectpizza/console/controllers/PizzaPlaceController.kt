package org.wit.perfectpizza.console.controllers

import mu.KotlinLogging
import org.wit.perfectpizza.console.models.PizzaPlaceJSONStore
//import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.models.PizzaPlaceModel
import org.wit.perfectpizza.console.views.PizzaPlaceView

class PizzaPlaceController {

    //val pizzaPlaces = PizzaPlaceMemStore()
    val pizzaPlaces = PizzaPlaceJSONStore()
    val pizzaPlaceView = PizzaPlaceView()
    val logger = KotlinLogging.logger{}

    init {
        logger.info { "Launching PerfectPizza Console App" }
        println("PerfectPizza Kotlin App Version 1.0")
    }
    fun start(){
        var input : Int

        do{
            input = menu()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> prepopulatedData()
                -1 -> println("Exiting App")
                else -> println("Invalid option. Try Again.")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting down PerfectPizza Console App" }
    }

    fun menu() : Int {
        return pizzaPlaceView.menu()
    }

    fun add(){
        var onePizzaPlace = PizzaPlaceModel()

        if(pizzaPlaceView.addPizzaPlaceData(onePizzaPlace))
            pizzaPlaces.create(onePizzaPlace)
        else
            logger.info("Pizza Place review not added.")
    }

    fun list() {
        pizzaPlaceView.listAllPizzaPlaces(pizzaPlaces)
    }

    fun update() {
        pizzaPlaceView.listAllPizzaPlaces(pizzaPlaces)
        var searchId= pizzaPlaceView.getId()
        val onePizzaPlace = search(searchId)

        if(onePizzaPlace != null) {
            if(pizzaPlaceView.updatePizzaPlaceData(onePizzaPlace)){
                pizzaPlaces.update(onePizzaPlace)
                pizzaPlaceView.showPizzaPlace(onePizzaPlace)
                logger.info("Pizza Place review updated : [ $onePizzaPlace ]")
            }
            else
                logger.info("Pizza Place review not updated.")
        }
        else
            logger.info("Pizza Place review not updated.")
    }

    fun delete() {
        pizzaPlaceView.listAllPizzaPlaces(pizzaPlaces)
        var searchId= pizzaPlaceView.getId()
        val onePizzaPlace = search(searchId)

        if(onePizzaPlace != null) {
            pizzaPlaces.delete(onePizzaPlace)
            println("Pizza Place Review [ $onePizzaPlace ] DELETED.")
            pizzaPlaceView.listAllPizzaPlaces(pizzaPlaces)
        }
        else
            println("Pizza Place Review not deleted...")
    }

    fun search() {
        val onePizzaPlace = search(pizzaPlaceView.getId())!!
        pizzaPlaceView.showPizzaPlace(onePizzaPlace)
    }

    fun search(id : Long) : PizzaPlaceModel? {
        var foundPizzaPlace = pizzaPlaces.findOne(id)
        return foundPizzaPlace
    }

    fun prepopulatedData() {
        pizzaPlaces.create(PizzaPlaceModel(1, "Robertos", "Carrick-On-Suir", "Tasty Pizza, too much cheese though", "Pepperoni", 7))
        pizzaPlaces.create(PizzaPlaceModel(2, "Jimmys", "Portlaw", "Pizza a bit too greasy", "Margherita", 4))
        pizzaPlaces.create(PizzaPlaceModel(3, "Dominoes", "Dungarvan", "Base was really thick when I asked for thin", "Meat Feast", 2))
    }

}