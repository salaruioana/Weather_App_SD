package com.sd.laborator.chaining

// "Regula" pe care o respectă fiecare verigă
interface WeatherChain {
    var next: WeatherChain?
    fun execute(request: WeatherRequest): String
}