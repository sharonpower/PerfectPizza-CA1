package org.wit.perfectpizza.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.perfectpizza.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_File =  "pizzaplaces.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<PizzaPlaceModel>> () {}.type

fun generateRandId(): Long {
    return Random().nextLong()
}


class PizzaPlaceJSONStore : PizzaPlaceStore {

    var pizzaPlaces = mutableListOf<PizzaPlaceModel>()

    init {
        if (exists(JSON_File)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<PizzaPlaceModel> {
        return pizzaPlaces
    }

    override fun findOne(id: Long) : PizzaPlaceModel? {
        var foundPizzaPlace: PizzaPlaceModel? = pizzaPlaces.find { p -> p.id == id }
        return foundPizzaPlace
    }

    override fun create(pizzaPlace: PizzaPlaceModel) {
        pizzaPlace.id = generateRandId()
        pizzaPlaces.add(pizzaPlace)
        serialize()
    }

    override fun update(pizzaPlace: PizzaPlaceModel) {
        var foundPizzaPlace = findOne(pizzaPlace.id!!)
        if (foundPizzaPlace != null) {
            foundPizzaPlace.name = pizzaPlace.name
            foundPizzaPlace.location = pizzaPlace.location
            foundPizzaPlace.choice = pizzaPlace.choice
            foundPizzaPlace.review = pizzaPlace.review
        }
        serialize()
    }

    override fun delete(pizzaPlace: PizzaPlaceModel) {
        pizzaPlaces.remove(pizzaPlace)
        serialize()
    }

    internal fun logAll() {
        pizzaPlaces.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(pizzaPlaces, listType)
        write(JSON_File, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_File)
        pizzaPlaces = Gson().fromJson(jsonString, listType)
    }
}