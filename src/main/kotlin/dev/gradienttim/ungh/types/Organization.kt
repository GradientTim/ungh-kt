package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OrganizationDto(
    val org: Organization,
)

@JsonClass(generateAdapter = true)
data class OrganizationRepositoriesDto(
    val repos: MutableList<Repository>,
)

data class Organization(
    val id: Int,
    val name: String,
    val description: String,
)
