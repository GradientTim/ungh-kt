package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RepositoryDto(
    val repo: Repository,
)

@JsonClass(generateAdapter = true)
data class RepositoryReadMe(
    val html: String,
    val markdown: String,
)

data class Repository(
    val id: Int,
    val name: String,
    val repo: String,
    val description: String?,
    val createdAt: String,
    val updatedAt: String,
    val pushedAt: String,
    val stars: Int,
    val watchers: Int,
    val forks: Int,
    val defaultBranch: String,
)
