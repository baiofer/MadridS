package com.jarzasa.madridshops.domain.model

import java.io.Serializable

/**
 * Shop: Represent one shop
 */
data class Shop(
        val id: Int,
        val name: String,
        val description: String,
        val latitude: Float,
        val longitude: Float,
        val image: String,
        val logo: String,
        val openingHours: String,
        val address: String): Serializable

/**
 * Shops: Represent a list of Shop
 */
class Shops( val shops: MutableList<Shop>): Aggregate<Shop>, Serializable, Iterator<Shop> by shops.iterator() {

    override fun count() = shops.size

    override fun all() = shops

    override fun get(position: Int) = shops.get(position)

    override fun get(name: String): Shop? {
        for (shop in 0..shops.count() -1) {
            if (shops[shop].name == name) {
                return shops[shop]
            }
        }
        return null
    }

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }
}