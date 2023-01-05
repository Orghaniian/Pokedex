package com.orghaniian.data.remote.model

import com.google.gson.annotations.SerializedName

data class NamedAPIResourceList(
    @SerializedName("count") val count: Int,
    @SerializedName("next") val next: String?,
    @SerializedName("previous") val previous: String?,
    @SerializedName("results") val results: List<NamedAPIResource>
)
