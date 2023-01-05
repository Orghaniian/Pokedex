package com.orghaniian.pokedex.data.remote.model

data class GetAllResponse<T> (
    val count: Int,
    val next: Boolean,
    val previous: Boolean,
    val results: List<T>
)
