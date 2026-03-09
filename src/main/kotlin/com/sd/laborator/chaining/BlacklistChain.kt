package com.sd.laborator.chaining

import com.sd.laborator.interfaces.BlacklistInterface
import org.springframework.stereotype.Component

@Component
class BlacklistChain(private val blacklistService: BlacklistInterface) : WeatherChain {
    override var next: WeatherChain? = null

    override fun execute(request: WeatherRequest): String{
        if(blacklistService.isCurrentNodeRestricted() || !blacklistService.isLocationAllowed(request.locationName)){
            return "Eroare chain: Accesul catre ${request.locationName} este interzis!"
        }
        return next?.execute(request) ?: "Eroare: Lant intrerupt dupa Backlist."
    }
}