package com.jarzasa.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

//Put this instruction if not parse all items
@JsonIgnoreProperties(ignoreUnknown = true)
data class ActivityEntity(
        val databaseId: Long,
        val id: Long,
        val name: String,
        val img: String = "",
        val logo_img: String = "",
        val opening_hours_en: String = "",
        val opening_hours_es: String = "",
        val opening_hours_jp: String = "",
        val opening_hours_cn: String = "",
        val opening_hours_cl: String = "",
        val opening_hours_mx: String = "",
        val telephone: String = "",
        val email: String = "",
        val url: String = "",
        val address: String = "",
        val description_en: String = "",
        val description_es: String = "",
        val description_jp: String = "",
        val description_cn: String = "",
        val description_cl: String = "",
        val description_mx: String = "",
        val gps_lat: String = "",
        val gps_lon: String = "",
        val special_offer: String = "",
        //val categories: String,
        val keywords_en: String = "",
        val keywords_es: String = "",
        val keywords_jp: String = "",
        val keywords_cn: String = "",
        val keywords_cl: String = "",
        val keywords_mx: String = "",
        val booking_operation: String = "",
        val booking_data: String = ""
)