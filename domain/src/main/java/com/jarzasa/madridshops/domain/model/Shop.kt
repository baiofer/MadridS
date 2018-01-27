package com.jarzasa.madridshops.domain.model

/**
 * Shop: Represent one shop
 */
data class Shop( val id: Int, val name: String, val address: String)

/**
 * Shops: Represent a list of Shop
 */
class Shops( val shops: MutableList<Shop>): Aggregate<Shop> {
    override fun count() = shops.size

    override fun all() = shops

    override fun get(position: Int) = shops.get(position)

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