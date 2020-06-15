package com.airtel.hiltapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.ClassRule
import org.junit.Rule
import org.mockito.MockitoAnnotations

/**
 * Created by SURYA N on 4/6/20.
 */
abstract class BaseTest {

    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxSchedulerRule()
    }

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
    }

}