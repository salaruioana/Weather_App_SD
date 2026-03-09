package com.sd.laborator.orchestration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherOrchController {
    @Autowired
    private lateinit var orchestartor: WeatherOrchestrator

    @RequestMapping("/orch/getforecast/{location}",method = [RequestMethod.GET])
    @ResponseBody
    fun getForecast(@PathVariable location: String): String {
        return orchestartor.getFullWeather(location)
    }
}