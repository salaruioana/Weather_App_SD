package com.sd.laborator.chaining

import com.sd.laborator.interfaces.WeatherForecastInterface
import org.springframework.stereotype.Component

@Component
class ForecastChain(private val weatherService: WeatherForecastInterface): WeatherChain {
    override var next: WeatherChain? = null

    override fun execute(request: WeatherRequest): String {
        var model = request.locationModel ?: return "Eroare chain: lipsesc datele de locatie. "

        //prognoza se va propaga inapoi
        return weatherService.getForecastData(model).toString()
    }
}