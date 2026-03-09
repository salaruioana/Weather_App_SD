package com.sd.laborator.interfaces

import com.sd.laborator.pojo.LocationModel
import com.sd.laborator.pojo.WeatherForecastData

//Injecția prin Constructor:Spring vede că serviciul are nevoie de TimeServiceInterface și îl aduce automat. Aceasta este cea mai sigură metodă de injecție (mai bună decât @Autowired pe câmpuri), pentru că face testarea mult mai ușoară.
interface WeatherForecastInterface {
    fun getForecastData(location: LocationModel): WeatherForecastData
}