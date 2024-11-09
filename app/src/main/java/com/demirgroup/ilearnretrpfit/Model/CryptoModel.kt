package com.demirgroup.ilearnretrpfit.Model

import com.google.gson.annotations.SerializedName

class CryptoModel (
    @SerializedName("name")
    val currencyName: String,
    @SerializedName("image")
    val currencyImage: String,
    @SerializedName("current_price")
    val currencyPrice:String
    )