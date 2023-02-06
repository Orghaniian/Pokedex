package com.orghaniian.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
internal  data class GetAllResponse<T> (
    val count: Int,
    val next: Boolean,
    val previous: Boolean,
    val results: List<T>
)
