package com.sd.laborator.orchestration

import com.sd.laborator.chaining.WeatherRequest
import com.sd.laborator.interfaces.BlacklistInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.stereotype.Service

@Service
class WeatherOrchestrator(
    private val blacklist: BlacklistInterface,
    private val locationSearch: LocationSearchInterface,
    private val weatherForecast: WeatherForecastInterface
) {
    fun getFullWeather(city: String): String {
        // seful verifica blacklist
        if(blacklist.isCurrentNodeRestricted() || !blacklist.isLocationAllowed(city)){
            return "Orchestrator: Locatia $city este blocata."
        }
        // seful cere coordonatele
        val coords = locationSearch.getLocationId(city)
            ?: return "ORCHESTARTOR: Locatia $city nu a fost gasita"
        // seful cere prognoza finala
        val data = weatherForecast.getForecastData(coords)

        return "Rezultat orchestrare: $data"
    }
}