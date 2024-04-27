package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BranchDto(
    val branches: MutableList<Branch>,
)

data class Branch(
    val name: String,
    val commit: BranchCommit,
    val protected: Boolean,
)

data class BranchCommit(
    val sha: String,
    val url: String,
)