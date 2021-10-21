package org.wit.perfectpizza.console.views

import org.wit.perfectpizza.console.main.pizzaPlaces
import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.models.PizzaPlaceModel

class PizzaPlaceView {

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
        print("Choose an option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option

    }

    fun listAllPizzaPlaces(pizzaPlaces : PizzaPlaceMemStore) {
        println("You chose to list all pizza place reviews")
        println()
        pizzaPlaces.logAll()
        println()
    }

    fun showPizzaPlace(pizzaPlace : PizzaPlaceModel) {
        if(pizzaPlace != null)
            println("Pizza Place Review Details : [ $pizzaPlace ]")
        else
            println("Pizza Place Review Not Found...")
    }

    fun addPizzaPlaceData(pizzaPlace: PizzaPlaceModel) : Boolean {

        println()
        print("Enter the Name of the pizza place : ")
        pizzaPlace.name = readLine()!!
        print("Enter where the pizza place is located : ")
        pizzaPlace.location = readLine()!!

        return  pizzaPlace.name.isNotEmpty() && pizzaPlace.location.isNotEmpty()

    }

    fun updatePizzaPlaceData(pizzaPlace: PizzaPlaceModel) : Boolean {

        var tempName : String?
        var tempLocation : String?

        if(pizzaPlace != null ) {
            print("Enter an updated Name for [ " + pizzaPlace.name +" ] : ")
            tempName = readLine()!!
            print("Enter the new location for [ " + pizzaPlace.name +
                    " ], the old one is [" + pizzaPlace.location +"] : ")
            tempLocation = readLine()!!

            if(!tempName.isNullOrEmpty() && !tempLocation.isNullOrEmpty()) {
                pizzaPlace.name = tempName
                pizzaPlace.location = tempLocation
                return true
            }
        }
        return false

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



}