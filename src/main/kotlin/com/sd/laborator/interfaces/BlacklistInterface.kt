package com.sd.laborator.interfaces

interface BlacklistInterface {
    fun isLocationAllowed(locationName: String): Boolean
    fun isCurrentNodeRestricted(): Boolean
}