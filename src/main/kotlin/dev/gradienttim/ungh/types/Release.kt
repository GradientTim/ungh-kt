package dev.gradienttim.ungh.types

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReleasesDto(
    val releases: MutableList<Release>,
)

@JsonClass(generateAdapter = true)
data class ReleaseDto(
    val release: Release,
)

data class Release(
    val id: Int,
    val tag: String,
    val author: String,
    val name: String,
    val draft: Boolean,

    @Json(name = "prerelease")
    val preRelease: Boolean,

    val createdAt: String,
    val publishedAt: String,
    val markdown: String,
    val html: String,
)
