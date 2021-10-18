package org.wit.perfectpizza.console.main

import mu.KotlinLogging
import org.wit.perfectpizza.console.models.PizzaPlaceModel

private val logger = KotlinLogging.logger {}

val pizzaPlaces = ArrayList<PizzaPlaceModel>()


fun main(args: Array<String>){
    logger.info { "Launching PerfectPizza Console App" }
    println("PerfectPizza Kotlin App Version 1.0")

    var input: Int

    do{
        input = menu()
        when(input) {
            1 -> addPizzaPlace()
            2 -> updatePizzaPlace()
            3 -> listAllPizzaPlaces()
            4 -> searchPizzaPlaces()
            -99 -> prepopulatedData()
            -1 -> println("Exiting App")
            else -> println("Invalid option. Try Again.")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting down PerfectPizza Console App" }



}

fun menu() : Int {

    var option : Int
    var input: String?

    println("Main Menu")
    println(" 1. Add a Pizza Place review")
    println(" 2. Update an existing Pizza Place review")
    println(" 3. List all reviewed Pizza Places")
    println(" 4. Search for a Pizza Place review")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option

}

fun addPizzaPlace(){
    var onePizzaPlace = PizzaPlaceModel()

    println("You chose to add a Pizza Place review")
    println()
    print("Enter the Name of the pizza place : ")
    onePizzaPlace.name = readLine()!!
    print("Enter where the pizza place is located : ")
    onePizzaPlace.location = readLine()!!

    if(onePizzaPlace.name.isNotEmpty() && onePizzaPlace.location.isNotEmpty()){
        onePizzaPlace.id == pizzaPlaces.size.toLong()
        pizzaPlaces.add(onePizzaPlace.copy())
        logger.info("Pizza Place review added : [ $onePizzaPlace ]")
    }
    else
        logger.info("Pizza Place review not added.")

    /*print("Enter the rating you would give this pizza place : ")
    var input : Int = readLine()!!*/
/*

        rating = input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
        else
            println("Error.")

        return rating as Unit
   , and gave this pizza place a rating of [ $rating ] ") */

}

fun updatePizzaPlace(){
    println("You chose to update an existing pizza place review")
    println()

    listAllPizzaPlaces()
    var searchId = getId()
    val onePizzaPlace = search(searchId)
    var tempName : String?
    var tempLocation : String?

    if(onePizzaPlace != null ) {
        print("Enter an updated Name for [ " + onePizzaPlace.name +" ] : ")
        tempName = readLine()!!
        print("Enter the new location for [ " + onePizzaPlace.name +
                " ], the old one is [" + onePizzaPlace.location +"] : ")
        tempLocation = readLine()!!
        if(!tempName.isNullOrEmpty() && !tempLocation.isNullOrEmpty()) {
            onePizzaPlace.name = tempName
            onePizzaPlace.location = tempLocation
            println("You updated the details of this review. " +
                    "The new name is [ " + onePizzaPlace.name +
                    " ] and it is in [ " + onePizzaPlace.location + " ]")
            logger.info("Pizza Place review updated : [ $onePizzaPlace ] ")
        }
        else
            logger.info("Pizza Place review not updated ...")

    }
    else
        println("Pizza Place review not updated ... ")

}

fun listAllPizzaPlaces() {
    println("You chose to list all pizza place reviews")
    println()
    pizzaPlaces.forEach { logger.info("${it}") }
}

fun searchPizzaPlaces() {
    var searchId = getId()
    var onePizzaPlace = search(searchId)

    if(onePizzaPlace != null)
        println("Pizza Place details are [ $onePizzaPlace ]")
    else
        println("Pizza Place not found ... :/ ")
}


fun getId() : Long {
    var stringId : String?
    var searchId : Long
    print("Enter id to Search/Update : ")
    stringId = readLine()!!
    searchId = if (stringId.toLongOrNull() != null && !stringId.isEmpty())
        stringId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : PizzaPlaceModel? {
    var foundPizzaPlace: PizzaPlaceModel? = pizzaPlaces.find { p -> p.id == id }
    return foundPizzaPlace
}

fun prepopulatedData() {
    pizzaPlaces.add(PizzaPlaceModel(1, "Robertos", "Carrick-On-Suir"))
    pizzaPlaces.add(PizzaPlaceModel(2, "Jimmys", "Portlaw"))
    pizzaPlaces.add(PizzaPlaceModel(3, "Dominoes", "Dungarvan"))
}
