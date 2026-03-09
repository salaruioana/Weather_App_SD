package com.sd.laborator.services

import com.sd.laborator.interfaces.BlacklistInterface
import org.springframework.stereotype.Service

@Service
class BlacklistService : BlacklistInterface{
    //lista
    private val blacklist = listOf("Bucharest","London","Vienna")
    override fun isLocationAllowed(locationName: String): Boolean{
        return !blacklist.any{it.equals(locationName, ignoreCase = true)}
    }
}