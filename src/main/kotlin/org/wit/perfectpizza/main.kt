package org.wit.perfectpizza

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

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
            -1 -> println("Exiting App")
            else -> println("Invalid option. Try Again.")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting down PerfectPizza Console App" }



}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add a Pizza Place review")
    println(" 2. Update an existing Pizza Place review")
    println(" 3. List all reviewed Pizza Places")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option


    return option
}

fun addPizzaPlace(){

    var name: String
    //var rating : Int

    println("You chose to add a Pizza Place review")
    println()
    print("Enter the Name of the pizza place : ")
    name = readLine()!!
    println("You entered [ $name ] for the name")



}

fun updatePizzaPlace(){
    println("You chose to update an existing pizza place review")
}

fun listAllPizzaPlaces() {
    println("You chose to list all pizza place reviews")
}
