package com.airtel.hiltapp.view

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.airtel.hiltapp.R
import com.airtel.hiltapp.model.Country
import com.airtel.hiltapp.network.ApiService
import com.airtel.hiltapp.utils.BaseTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.Single
import kotlinx.android.synthetic.main.activity_main.view.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify

/**
 * Created by SURYA N on 5/6/20.
 */
@HiltAndroidTest
class MainActivityTest: BaseTest() {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Mock
    lateinit var api: ApiService


    private fun setApiValues() {
        val country = Country("India", "")
        val countriesList = arrayListOf<Country>()
        for (i in 0 until 20)
            countriesList.add(country)
        Mockito.`when`(api.getCountries()).thenReturn(Single.just(countriesList))
    }

    @Before
    fun setup(){
        activityScenarioRule.scenario.use {
            it.moveToState(Lifecycle.State.CREATED)
            it.onActivity {
                it.viewModel
            }
        }
        hiltRule.inject()
    }

    @Test
    fun launchApp() {
        setApiValues()
        //onView(withId(R.id.rv_list)).check(matches(isDisplayed()))
    }

}