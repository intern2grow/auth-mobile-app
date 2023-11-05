package dev.awd.auth.data.responses

import com.google.gson.annotations.SerializedName


data class Bank(

    @SerializedName("cardExpire") var cardExpire: String? = null,
    @SerializedName("cardNumber") var cardNumber: String? = null,
    @SerializedName("cardType") var cardType: String? = null,
    @SerializedName("currency") var currency: String? = null,
    @SerializedName("iban") var iban: String? = null

)