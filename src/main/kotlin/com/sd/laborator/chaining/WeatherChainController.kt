package com.sd.laborator.chaining

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WeatherChainController {

    @Autowired
    private lateinit var chainConfig: ChainConfig

    @RequestMapping("/chain/getforecast/{location}", method = [RequestMethod.GET])
    @ResponseBody
    fun getForeacast(@PathVariable location: String): String{
        //facem cutia
        val request = WeatherRequest(locationName = location)

        //trimitem cutia la prima veriga
        return chainConfig.getChainStart().execute(request)
    }
}