package org.wit.perfectpizza.console.views

import org.wit.perfectpizza.console.models.PizzaPlaceJSONStore
import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.models.PizzaPlaceModel
import java.lang.NumberFormatException


class PizzaPlaceView {

    fun menu() : Int {

        var option : Int
        var input: String?

        com.github.mm.coloredconsole.println{"___________________________________________".red.bg.faint}
        com.github.mm.coloredconsole.println{"________________MAIN MENU__________________".bright.bold.underline}
        com.github.mm.coloredconsole.println{"___________________________________________".red.bg.faint}

        com.github.mm.coloredconsole.println{" 1. Add a Pizza Place review".italic.reverse}
        com.github.mm.coloredconsole.println{" 2. Update an existing Pizza Place review".italic.reverse}
        com.github.mm.coloredconsole.println{" 3. List all reviewed Pizza Places".italic.reverse}
        com.github.mm.coloredconsole.println{" 4. Search for a Pizza Place review".italic.reverse}
        com.github.mm.coloredconsole.println{" 5. Delete a review for a Pizza Place".italic.reverse}
        com.github.mm.coloredconsole.println{"-1. Exit".italic.reverse}
        println()
        com.github.mm.coloredconsole.println{"_____________Choose an Option :____________".underline.italic}
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
        try{
            println()
            print("Enter the Name of the pizza place : ")
            pizzaPlace.name = readLine()!!
            print("Enter where the pizza place is located : ")
            pizzaPlace.location = readLine()!!
            print("Enter the type of pizza you ordered here :")
            pizzaPlace.choice = readLine()!!
            print("Enter your review : ")
            pizzaPlace.review = readLine()!!
            print("Enter your rating : ")
            pizzaPlace.rating = readLine()!!.toInt()
        }
        catch(e: NumberFormatException) {
            println()
            com.github.mm.coloredconsole.println{"Please do not leave a field empty!".bright.yellow.bold.reverse}
            println()
        }


        return  pizzaPlace.name.isNotEmpty() && pizzaPlace.location.isNotEmpty() &&
                pizzaPlace.review.isNotEmpty() && pizzaPlace.choice.isNotEmpty() &&
                pizzaPlace.rating.toString().isNotEmpty()

    }

    fun updatePizzaPlaceData(pizzaPlace: PizzaPlaceModel) : Boolean {
        try{

            var tempName : String?
            var tempLocation : String?
            var tempChoice : String?
            var tempReview : String?
            var tempRating : Int?

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
                print("Update your rating for for [" + pizzaPlace.name + "] :")
                tempRating= readLine()!!.toInt()


                if(!tempName.isNullOrEmpty() && !tempLocation.isNullOrEmpty() && !tempReview.isNullOrEmpty() && !tempRating.toString().isNullOrEmpty()) {
                    pizzaPlace.name = tempName
                    pizzaPlace.location = tempLocation
                    pizzaPlace.choice = tempChoice
                    pizzaPlace.review = tempReview
                    pizzaPlace.rating = tempRating
                    return true
                }
            }
        }
        catch (e: NumberFormatException){
            println()
            com.github.mm.coloredconsole.println{"Please do not leave a field empty!".yellow.reverse}
            println()
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