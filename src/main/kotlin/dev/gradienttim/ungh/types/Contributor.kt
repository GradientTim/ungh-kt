package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContributorDto(
    val contributors: MutableList<Contributor>,
)

data class Contributor(
    val id: Int,
    val username: String,
    val contributions: Int,
)
