package com.sd.laborator.chaining

import com.sd.laborator.services.LocationSearchService
import org.springframework.stereotype.Component

@Component
class GeocodingChain(private val locationService: LocationSearchService) : WeatherChain {
    override var next: WeatherChain? = null

    override fun execute(request: WeatherRequest): String {
        val model = locationService.getLocationId(request.locationName)

        if(model == null){
            return "Eroare chain: NU s-a gasit componenta pentru localizarea geografica ${request.locationName}."
        }

        //punem coordonatele in cutie pentru urmatoarea veriga
        request.locationModel = model

        //trimitem mai departe
        return next?.execute(request)?:"Eroare in lant, intrerupere la geolocalizare."
    }

}