package com.sd.laborator.services

import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.pojo.LocationModel
import org.springframework.stereotype.Service
import java.net.URL
import org.json.JSONObject
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Service
class LocationSearchService : LocationSearchInterface {
    override fun getLocationId(locationName: String): LocationModel? {
        // codificare parametru URL (deoarece poate conţine caractere speciale)
        val encodedLocationName = URLEncoder.encode(locationName, StandardCharsets.UTF_8.toString())
        val locationSearchURL = URL("https://geocoding-api.open-meteo.com/v1/search?name=$encodedLocationName&count=1&language=en&format=json")

        // construire obiect de tip URL
        //https://geocoding-api.open-meteo.com/v1/search?name=$locationId&count=1
        //val locationSearchURL = URL("https://www.metaweather.com/api/location/search/?query=$encodedLocationName")


        return try{
            val response = locationSearchURL.readText()
            val json = JSONObject(response)

            if (!json.has("results")) return null

            val res = json.getJSONArray("results").getJSONObject(0)

            // Mapăm JSON-ul specific la obiectul nostru generic
            LocationModel(
                latitude = res.getDouble("latitude"),
                longitude = res.getDouble("longitude"),
            )
        }catch(e: Exception){
            null
        }
    }
}