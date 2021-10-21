package org.wit.perfectpizza.console.main

import mu.KotlinLogging
import org.wit.perfectpizza.console.controllers.PizzaPlaceController
import org.wit.perfectpizza.console.models.PizzaPlaceJSONStore
import org.wit.perfectpizza.console.models.PizzaPlaceMemStore
import org.wit.perfectpizza.console.views.PizzaPlaceView
import java.awt.SystemColor.menu

private val logger = KotlinLogging.logger {}

val pizzaPlaces = PizzaPlaceJSONStore()
val pizzaPlaceView = PizzaPlaceView()

fun main(args: Array<String>){
    PizzaPlaceController().start()
}
