package com.sd.laborator.controllers

import com.sd.laborator.interfaces.BlacklistInterface
import com.sd.laborator.interfaces.LocationSearchInterface
import com.sd.laborator.interfaces.WeatherForecastInterface
import com.sd.laborator.pojo.LocationModel
import com.sd.laborator.pojo.WeatherForecastData
import com.sd.laborator.services.TimeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

//@Controller: Îi spune lui Spring că această clasă gestionează cereri HTTP.
@Controller
class WeatherAppController {
    @Autowired
    //@Autowired: Exact ca @EJB. Spring caută o clasă care implementează LocationSearchInterface și o "injectează" aici.
    private lateinit var locationSearchService: LocationSearchInterface

    @Autowired
    private lateinit var weatherForecastService: WeatherForecastInterface

    @Autowired
    private lateinit var blacklistService: BlacklistInterface // Injectam filtrul

    //@RequestMapping: Înlocuiește acele zeci de linii din web.xml. Ai pus @PathVariable location, deci dacă accesezi /getforecast/Iasi, variabila location va deveni "Iasi".
    @RequestMapping("/getforecast/{location}", method = [RequestMethod.GET])
    //@ResponseBody: Îi spune lui Spring: "Nu căuta o pagină HTML, ci scrie acest String direct în corpul răspunsului HTTP".
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {

        if(blacklistService.isCurrentNodeRestricted()){
            return "Acces interzis! Nu furnizam informatii pentru locatia dumneavoastra."
        }
        if (!blacklistService.isLocationAllowed(location)) {
            return "Acces interzis! Informațiile meteo pentru zona \"$location\" sunt restricționate conform politicilor de securitate locale."
        }
        // se incearca preluarea locaţiei primite in URL
        val locationModel: LocationModel? = locationSearchService.getLocationId(location)

        // dacă locaţia nu a fost găsită, răspunsul va fi corespunzător
        if (locationModel == null) {
            return "Nu s-au putut găsi coordonate geografice pentru locația \"$location\"!"
        }

        // pe baza ID-ului de locaţie, se interoghează al doilea serviciu care returnează datele meteo
        // încapsulate într-un obiect POJO
        val rawForecastData: WeatherForecastData = weatherForecastService.getForecastData(locationModel)

        // fiind obiect POJO, funcţia toString() este suprascrisă pentru o afişare mai prietenoasă
        return rawForecastData.toString()
    }
}