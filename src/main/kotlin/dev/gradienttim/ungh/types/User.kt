package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDto(
    val user: User,
)

@JsonClass(generateAdapter = true)
data class UserQueryDto(
    val user: User,
)

@JsonClass(generateAdapter = true)
data class UserRepositoriesDto(
    val repos: MutableList<Repository>,
)

data class User(
    val id: Int,
    val username: String,
    val name: String?,
    val twitter: String?,
    val avatar: String?,
)
