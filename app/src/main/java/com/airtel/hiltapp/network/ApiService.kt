package com.airtel.hiltapp.network

import com.airtel.hiltapp.model.Country
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by SURYA N on 4/6/20.
 */
interface ApiService {

    //
    @GET("linssen/country-flag-icons/master/countries.json")
    fun getCountries() : Single<List<Country>>

}