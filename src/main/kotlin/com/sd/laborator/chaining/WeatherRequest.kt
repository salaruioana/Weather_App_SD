package com.sd.laborator.chaining

import com.sd.laborator.pojo.LocationModel

//data class pentru transport -DTO
// "Cutia" care circulă pe bandă
data class WeatherRequest(
    val locationName: String,
    var locationModel: LocationModel? = null
)
