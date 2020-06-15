package com.airtel.hiltapp.network

import com.airtel.hiltapp.model.Country
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by SURYA N on 4/6/20.
 */
class CountryService @Inject constructor(private val api : ApiService) {

    fun getCountries(): Single<List<Country>> {
        return api.getCountries()
    }
}