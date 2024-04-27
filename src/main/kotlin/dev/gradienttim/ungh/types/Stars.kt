package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StarsDto(
    val totalStars: Int,
    val stars: MutableMap<String, Int>,
)