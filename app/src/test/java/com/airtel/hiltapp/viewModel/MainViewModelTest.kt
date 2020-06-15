package com.airtel.hiltapp.viewModel

import androidx.lifecycle.SavedStateHandle
import com.airtel.hiltapp.BaseTest
import com.airtel.hiltapp.model.Country
import com.airtel.hiltapp.network.CountryService
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by SURYA N on 4/6/20.
 */
class MainViewModelTest : BaseTest(){

    @Mock
    lateinit var service: CountryService

    lateinit var viewModel: MainViewModel

    private var testScheduler : Single<List<Country>> ?= null

    private fun setSchedulerData(){
        testScheduler = Single.just(arrayListOf(Country("India","")))
    }

    @Before
    fun onStart(){
        viewModel = MainViewModel(service, SavedStateHandle())
    }


    @Test
    fun getCountriesList() {
        setSchedulerData()
        Mockito.`when`(service.getCountries()).thenReturn(testScheduler)
        viewModel.getCountryList()
        Assert.assertEquals(1,viewModel.countryLiveData.value?.size)
    }

    @Test
    fun getCountriesFail(){
        testScheduler = Single.error(Throwable())
        Mockito.`when`(service.getCountries()).thenReturn(testScheduler)
        viewModel.getCountryList()
        Assert.assertEquals(null,viewModel.countryLiveData.value?.size)
    }
}