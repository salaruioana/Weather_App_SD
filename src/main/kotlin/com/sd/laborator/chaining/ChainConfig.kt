package com.sd.laborator.chaining

import org.springframework.context.annotation.Configuration
import javax.annotation.PostConstruct

@Configuration
open class ChainConfig(
    private val blacklistChain: BlacklistChain,
    private val geocodingChain: GeocodingChain,
    private val forecastChain: ForecastChain
) {
    @PostConstruct
    fun connectDots(){
        // stabilim ordinea de inlantuire
        blacklistChain.next = geocodingChain
        geocodingChain.next = forecastChain
    }

    //functia pe care o va folosi Controllerul pentru a porni procesul
    fun getChainStart(): WeatherChain = blacklistChain
}