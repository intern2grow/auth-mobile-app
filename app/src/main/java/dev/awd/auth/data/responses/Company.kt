package dev.awd.auth.data.responses

import com.google.gson.annotations.SerializedName


data class Company(

    @SerializedName("address") var address: Address? = Address(),
    @SerializedName("department") var department: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("title") var title: String? = null

)