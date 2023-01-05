package com.orghaniian.pokedex.data.remote.model

import com.google.gson.annotations.SerializedName

data class APIResource(
    @SerializedName("name") val name: String,
)
