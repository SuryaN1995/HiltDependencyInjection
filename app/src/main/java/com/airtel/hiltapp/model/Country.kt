package com.airtel.hiltapp.model

import com.google.gson.annotations.SerializedName

/**
 * Created by SURYA N on 4/6/20.
 */
data class Country(var name:String, @SerializedName("file_url")var url:String)