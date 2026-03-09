package com.sd.laborator.services

import com.sd.laborator.interfaces.TimeServiceInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.LocationModel
import com.sd.laborator.pojo.WeatherForecastData
import org.json.JSONObject
import org.springframework.stereotype.Service
import java.net.URL
import kotlin.math.roundToInt

@Service
class WeatherForecastService (private val timeService: TimeServiceInterface) : WeatherForecastInterface {
    override fun getForecastData(location: LocationModel): WeatherForecastData {
        // ID-ul locaţiei nu trebuie codificat, deoarece este numeric
        val forecastDataURL = URL("https://api.open-meteo.com/v1/forecast?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true")

        // preluare conţinut răspuns HTTP la o cerere GET către URL-ul de mai sus
        val rawResponse: String = forecastDataURL.readText()

        // parsare obiect JSON primit
        val responseRootObject = JSONObject(rawResponse)
        val current = responseRootObject.getJSONObject("current_weather")

        return WeatherForecastData(
            latitude = location.latitude,
            longitude = location.longitude,
            date = timeService.getCurrentTime(),
            temperature = current.getDouble("temperature").roundToInt(),
            windSpeed = current.getDouble("windspeed"),
            conditionCode = current.getInt("weathercode"),
            description = decodeWmoCode(current.getInt("weathercode"))
        )
    }

    private fun decodeWmoCode(code: Int): String = when(code) {
        0 -> "Cer senin"
        1, 2, 3 -> "Noros"
        61, 63, 65 -> "Ploaie"
        else -> "Condiții variate ($code)"
    }
}