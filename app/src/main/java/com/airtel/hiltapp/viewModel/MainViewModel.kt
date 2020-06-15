package com.airtel.hiltapp.viewModel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.airtel.hiltapp.custom.loadSub
import com.airtel.hiltapp.model.Country
import com.airtel.hiltapp.network.CountryService
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver

/**
 * Created by SURYA N on 4/6/20.
 */
class MainViewModel @ViewModelInject constructor(
    private val api: CountryService,
    @Assisted private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val disposable = CompositeDisposable()

    val countryLiveData = MutableLiveData<List<Country>>().apply {
        postValue(null)
    }


    fun getCountryList() {
        disposable.add(
            api.getCountries().loadSub().subscribeWith(object :
                DisposableSingleObserver<List<Country>>() {
                override fun onSuccess(data: List<Country>) {
                    countryLiveData.value = data
                }

                override fun onError(e: Throwable) {
                    countryLiveData.value = null
                }
            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }

}