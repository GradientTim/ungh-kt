package dev.gradienttim.ungh

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.gradienttim.ungh.types.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody

object Ungh {

    private val HTTP_CLIENT: OkHttpClient = OkHttpClient.Builder().build()
    private val MOSHI: Moshi = Moshi.Builder()
        .addLast(KotlinJsonAdapterFactory())
        .build()

    private val REPOSITORY_ADAPTER = MOSHI.adapter(RepositoryDto::class.java)
    private val REPOSITORY_CONTRIBUTORS_ADAPTER = MOSHI.adapter(ContributorDto::class.java)
    private val REPOSITORY_BRANCHES_ADAPTER = MOSHI.adapter(BranchDto::class.java)
    private val REPOSITORY_FILES_ADAPTER = MOSHI.adapter(BranchFilesDto::class.java)
    private val REPOSITORY_FILE_ADAPTER = MOSHI.adapter(BranchFileDto::class.java)
    private val REPOSITORY_README_ADAPTER = MOSHI.adapter(RepositoryReadMe::class.java)
    private val REPOSITORY_RELEASES_ADAPTER = MOSHI.adapter(ReleasesDto::class.java)
    private val REPOSITORY_RELEASE_ADAPTER = MOSHI.adapter(ReleaseDto::class.java)

    private val ORGANIZATION_ADAPTER = MOSHI.adapter(OrganizationDto::class.java)
    private val ORGANIZATION_REPOSITORIES_ADAPTER = MOSHI.adapter(OrganizationRepositoriesDto::class.java)

    private val STARS_ADAPTER = MOSHI.adapter(StarsDto::class.java)

    private val USER_ADAPTER = MOSHI.adapter(UserDto::class.java)
    private val USER_REPOSITORIES_ADAPTER = MOSHI.adapter(UserRepositoriesDto::class.java)
    private val USER_QUERY_ADAPTER = MOSHI.adapter(UserQueryDto::class.java)

    private fun createRequest(
        method: String,
        url: String,
        body: RequestBody? = null,
    ): Request = Request.Builder()
        .addHeader("User-Agent", "UnghKt/1.0")
        .method(method, body)
        .url(url)
        .build()

    /**
     * Fetches information about a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A [dev.gradienttim.ungh.types.Repository] object containing information about the repository, or null if the repository is not found.
     */
    fun repository(owner: String, name: String): Repository? {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            val dto = REPOSITORY_ADAPTER.fromJson(body.source()) ?: return null
            return dto.repo
        }
        return null
    }

    /**
     * Fetches a list of contributors for a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A mutable list of [dev.gradienttim.ungh.types.Contributor] objects representing the contributors to the repository.
     */
    fun repositoryContributors(owner: String, name: String): MutableList<Contributor> {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_CONTRIBUTORS.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return mutableListOf()
            val dto = REPOSITORY_CONTRIBUTORS_ADAPTER.fromJson(body.source()) ?: return mutableListOf()
            return dto.contributors
        }
        return mutableListOf()
    }

    /**
     * Fetches information about the files in a specific branch of a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @param branch The name of the branch (e.g., "main").
     * @return A [dev.gradienttim.ungh.types.BranchFilesDto] object containing information about the files in the branch, or null if the branch does not exist.
     */
    fun repositoryBranchFiles(owner: String, name: String, branch: String): BranchFilesDto? {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_FILES.format(
            "owner" to owner,
            "name" to name,
            "branch" to branch,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            return REPOSITORY_FILES_ADAPTER.fromJson(body.source())
        }
        return null
    }

    /**
     * Fetches information about a specific file in a branch of a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @param branch The name of the branch (e.g., "main").
     * @param path The path of the file within the branch (e.g., "src/index.js").
     * @return A [dev.gradienttim.ungh.types.BranchFileDto] object containing information about the file, or null if the file does not exist in the branch.
     */
    fun repositoryBranchFile(owner: String, name: String, branch: String, path: String): BranchFileDto? {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_FILE.format(
            "owner" to owner,
            "name" to name,
            "branch" to branch,
            "path" to path,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            return REPOSITORY_FILE_ADAPTER.fromJson(body.source())
        }
        return null
    }

    /**
     * Fetches the README file for a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A [dev.gradienttim.ungh.types.RepositoryReadMe] object containing the README content, or null if the repository does not have a README.
     */
    fun repositoryReadMe(owner: String, name: String): RepositoryReadMe? {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_README.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            return REPOSITORY_README_ADAPTER.fromJson(body.source())
        }
        return null
    }

    /**
     * Fetches a list of releases for a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A mutable list of [dev.gradienttim.ungh.types.Release] objects representing the releases of the repository.
     */
    fun repositoryReleases(owner: String, name: String): MutableList<Release> {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_RELEASES.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return mutableListOf()
            val dto = REPOSITORY_RELEASES_ADAPTER.fromJson(body.source()) ?: return mutableListOf()
            return dto.releases
        }
        return mutableListOf()
    }

    /**
     * Fetches information about the latest release for a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A [dev.gradienttim.ungh.types.Release] object representing the latest release of the repository, or null if the repository has no releases.
     */
    fun repositoryLatestRelease(owner: String, name: String): Release? {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_RELEASES_LATEST.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            val dto = REPOSITORY_RELEASE_ADAPTER.fromJson(body.source()) ?: return null
            return dto.release
        }
        return null
    }

    /**
     * Fetches a list of branches for a repository.
     *
     * @param owner The owner of the repository (e.g., "unjs").
     * @param name The name of the repository (e.g., "ungh").
     * @return A mutable list of [dev.gradienttim.ungh.types.Branch] objects representing the branches of the repository.
     */
    fun repositoryBranches(owner: String, name: String): MutableList<Branch> {
        val request = createRequest("GET", UnghEndpoint.REPOSITORY_BRANCHES.format(
            "owner" to owner,
            "name" to name,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return mutableListOf()
            val dto = REPOSITORY_BRANCHES_ADAPTER.fromJson(body.source()) ?: return mutableListOf()
            return dto.branches
        }
        return mutableListOf()
    }

    /**
     * Fetches information about an organization.
     *
     * @param owner The name of the organization (e.g., "unjs").
     * @return An [dev.gradienttim.ungh.types.Organization] object containing information about the organization, or null if the organization does not exist.
     */
    fun organization(owner: String): Organization? {
        val request = createRequest("GET", UnghEndpoint.ORGANISATION.format(
            "owner" to owner,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            val dto = ORGANIZATION_ADAPTER.fromJson(body.source()) ?: return null
            return dto.org
        }
        return null
    }

    /**
     * Fetches a list of repositories for an organization.
     *
     * @param owner The name of the organization (e.g., "unjs").
     * @return A mutable list of [dev.gradienttim.ungh.types.Repository] objects representing the organization's repositories.
     */
    fun organizationRepositories(owner: String): MutableList<Repository> {
        val request = createRequest("GET", UnghEndpoint.ORGANISATION_REPOSITORIES.format(
            "owner" to owner,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return mutableListOf()
            val dto = ORGANIZATION_REPOSITORIES_ADAPTER.fromJson(body.source()) ?: return mutableListOf()
            return dto.repos
        }
        return mutableListOf()
    }

    /**
     * Fetches information about the users who starred a repository.
     * This function can potentially be used with both full repository URLs or the format "owner/name".
     *
     * @param repository The URL or the owner/name format of the repository (e.g., "https://github.com/unjs/ungh" or "unjs/ungh").
     * @return A [dev.gradienttim.ungh.types.StarsDto] object containing information about the users who starred the repository, or null if the information is not available.
     * */
    fun stars(repository: String): StarsDto? = stars(listOf(repository))

    /**
     * Fetches information about the users who starred a list of repositories.
     *
     * This function can potentially be used with both full repository URLs or the format "owner/name" for each repository in the list.
     *
     * @param repositories A list of URLs or owner/name formats of the repositories.
     * @return A [dev.gradienttim.ungh.types.StarsDto] object containing information about the users who starred the repositories, or null if the information is not available.
     */
    fun stars(repositories: List<String>): StarsDto? {
        val urlQuery = repositories.joinToString(separator = "+")
        val request = createRequest("GET", UnghEndpoint.STARS_REPOSITORIES.format(
            "repositories" to urlQuery,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            return STARS_ADAPTER.fromJson(body.source())
        }
        return null
    }

    /**
     * Fetches information about a user.
     *
     * @param username The username of the user (e.g., "GradientTim").
     * @return A [dev.gradienttim.ungh.types.User] object containing information about the user, or null if the user does not exist.
     */
    fun user(username: String): User? {
        val request = createRequest("GET", UnghEndpoint.USER.format(
            "username" to username,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            val dto = USER_ADAPTER.fromJson(body.source()) ?: return null
            return dto.user
        }
        return null
    }

    /**
     * Fetches a list of repositories for a user.
     *
     * @param username The username of the user (e.g., "GradientTim").
     * @return A mutable list of [dev.gradienttim.ungh.types.Repository] objects representing the user's repositories.
     */
    fun userRepositories(username: String): MutableList<Repository> {
        val request = createRequest("GET", UnghEndpoint.USER_REPOSITORIES.format(
            "username" to username,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return mutableListOf()
            val dto = USER_REPOSITORIES_ADAPTER.fromJson(body.source()) ?: return mutableListOf()
            return dto.repos
        }
        return mutableListOf()
    }

    /**
     * Fetches information about a user by username or ID.
     *
     * @param query The username or email of the user.
     * @return A [dev.gradienttim.ungh.types.User] object containing information about the user, or null if the user does not exist.
     */
    fun userQuery(query: String): User? {
        val request = createRequest("GET", UnghEndpoint.USER_QUERY.format(
            "query" to query,
        ))
        val response = HTTP_CLIENT.newCall(request).execute()
        if (response.code == 200) {
            val body = response.body ?: return null
            val dto = USER_QUERY_ADAPTER.fromJson(body.source()) ?: return null
            return dto.user
        }
        return null
    }

}