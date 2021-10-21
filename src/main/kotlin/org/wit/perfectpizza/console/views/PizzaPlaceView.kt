package org.wit.perfectpizza.console.views

import org.wit.perfectpizza.console.models.PizzaPlaceJSONStore
import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.models.PizzaPlaceModel


class PizzaPlaceView {

    fun menu() : Int {

        var option : Int
        var input: String?

        com.github.mm.coloredconsole.println{"___________________________________________".red.bg.faint}
        com.github.mm.coloredconsole.println{"________________MAIN MENU__________________".white.underline}
        com.github.mm.coloredconsole.println{"___________________________________________".red.bg.faint}

        com.github.mm.coloredconsole.println{" 1. Add a Pizza Place review".italic}
        com.github.mm.coloredconsole.println{" 2. Update an existing Pizza Place review".italic}
        com.github.mm.coloredconsole.println{" 3. List all reviewed Pizza Places".italic}
        com.github.mm.coloredconsole.println{" 4. Search for a Pizza Place review".italic}
        com.github.mm.coloredconsole.println{" 5. Delete a review for a Pizza Place".italic}
        com.github.mm.coloredconsole.println{"-1. Exit".italic}
        println()
        com.github.mm.coloredconsole.println{"_____________Choose an Option :____________".white.underline.italic}
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option

    }

    fun listAllPizzaPlaces(pizzaPlaces : PizzaPlaceJSONStore) {
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
        print("Enter the type of pizza you ordered here :")
        pizzaPlace.choice = readLine()!!
        print("Enter your review : ")
        pizzaPlace.review = readLine()!!

        return  pizzaPlace.name.isNotEmpty() && pizzaPlace.location.isNotEmpty() && pizzaPlace.review.isNotEmpty() && pizzaPlace.choice.isNotEmpty()

    }

    fun updatePizzaPlaceData(pizzaPlace: PizzaPlaceModel) : Boolean {

        var tempName : String?
        var tempLocation : String?
        var tempChoice : String?
        var tempReview : String?

        if(pizzaPlace != null ) {
            print("Enter an updated Name for [ " + pizzaPlace.name +" ] : ")
            tempName = readLine()!!
            print("Enter the new location for [ " + pizzaPlace.name +
                    " ], the old one is [" + pizzaPlace.location +"] : ")
            tempLocation = readLine()!!
            print("Update the pizza you purchased at [ " + pizzaPlace.name +
                    "] : ")
            tempChoice = readLine()!!
            print("Update your review for [" + pizzaPlace.name + "] :")
            tempReview = readLine()!!


            if(!tempName.isNullOrEmpty() && !tempLocation.isNullOrEmpty() && !tempReview.isNullOrEmpty()) {
                pizzaPlace.name = tempName
                pizzaPlace.location = tempLocation
                pizzaPlace.choice = tempChoice
                pizzaPlace.review = tempReview
                return true
            }
        }
        return false

    }

    fun getId() : Long {
        var stringId : String?
        var searchId : Long
        print("Enter id to Search/Update/Delete : ")
        stringId = readLine()!!
        searchId = if (stringId.toLongOrNull() != null && !stringId.isEmpty())
            stringId.toLong()
        else
            -9
        return searchId
    }
}