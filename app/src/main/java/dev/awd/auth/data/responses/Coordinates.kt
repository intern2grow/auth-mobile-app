package dev.awd.auth.data.responses

import com.google.gson.annotations.SerializedName


data class Coordinates(

    @SerializedName("lat") var lat: String? = null,
    @SerializedName("lng") var lng: String? = null

)