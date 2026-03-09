package com.sd.laborator.services

import com.sd.laborator.interfaces.BlacklistInterface
import org.springframework.stereotype.Service
import java.net.URL

@Service
class BlacklistService : BlacklistInterface{
    //lista
    // o modalitate de dezvoltare ar putea fi preluarea locatiilor restrictionate dintr-un fisier
    private val blacklist = listOf("bucharest","london","vienna")
    private val blacklistedZones = listOf("tokyo","barcelona","iași")
    override fun isLocationAllowed(locationName: String): Boolean{
        return !blacklist.any{it.equals(locationName, ignoreCase = true)}
    }
    override fun isCurrentNodeRestricted(): Boolean{
        return try{
            //detectam locatia serverului (nodului de calcul) prin IP
            //folosim un api gratuit de geolocalizare
            val myCity = URL("https://ipapi.co/city/").readText().trim().lowercase()
            println("Nodul de calcul se afla in $myCity")
            blacklistedZones.contains(myCity)
        }catch(e: Exception){
            false
        }
    }
}