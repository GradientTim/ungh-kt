package dev.gradienttim.ungh.types

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BranchFilesDto(
    val meta: FileMeta,
    val files: MutableList<BranchFile>,
)

@JsonClass(generateAdapter = true)
data class BranchFileDto(
    val meta: SimpleFileMeta,
    val file: SimpleFile,
)

data class FileMeta(
    val sha: String,
)

data class SimpleFileMeta(
    val url: String,
)

data class BranchFile(
    val path: String,
    val mode: String,
    val sha: String,
    val size: Int,
)

data class SimpleFile(
    val contents: String,
    val html: String? = null,
)