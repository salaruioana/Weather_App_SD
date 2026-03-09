package com.sd.laborator.pojo

//data class generează automat Getter, Setter, equals(), hashCode() și toString().
data class WeatherForecastData (
    var latitude: Double,
    var longitude: Double,
    var date: String,
    var temperature: Int,
    var windSpeed: Double,
    var conditionCode: Int, // Codul WMO
    var description: String
)
