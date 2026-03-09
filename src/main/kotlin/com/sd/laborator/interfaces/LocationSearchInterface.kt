package com.sd.laborator.interfaces

import com.sd.laborator.pojo.LocationModel

interface LocationSearchInterface {
    fun getLocationId(locationName: String): LocationModel?
}