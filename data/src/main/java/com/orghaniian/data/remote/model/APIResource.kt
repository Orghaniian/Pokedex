package com.orghaniian.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
 internal data class APIResource(
    @SerialName("url") val url: String,
)
