package com.jarzasa.madridshops.domain.model

/**
 * Activity: Represent one activity
 */
data class Activity(
        val id: Int,
        val name: String,
        val description: String,
        val latitude: Float,
        val longitude: Float,
        val image: String,
        val logo: String,
        val openingHours: String,
        val address: String)

/**
 * Activities: Represent a list of Activity
 */
class Activities( val activities: MutableList<Activity>): Aggregate<Activity> {
    override fun count() = activities.size

    override fun all() = activities

    override fun get(position: Int) = activities.get(position)

    override fun add(element: Activity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }

    override fun delete(element: Activity) {
        activities.remove(element)
    }
}