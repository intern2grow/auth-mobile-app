package dev.awd.auth.data.responses

import com.google.gson.annotations.SerializedName


data class Hair(

    @SerializedName("color") var color: String? = null,
    @SerializedName("type") var type: String? = null

)