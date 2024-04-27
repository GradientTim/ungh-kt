package dev.gradienttim.ungh

enum class UnghEndpoint(
    private val path: String,
) {

    REPOSITORY("repos/{owner}/{name}"),
    REPOSITORY_CONTRIBUTORS("repos/{owner}/{name}/contributors"),
    REPOSITORY_FILES("repos/{owner}/{name}/files/{branch}"),
    REPOSITORY_FILE("repos/{owner}/{name}/files/{branch}/{path}"),
    REPOSITORY_README("repos/{owner}/{name}/readme"),
    REPOSITORY_RELEASES("repos/{owner}/{name}/releases"),
    REPOSITORY_RELEASES_LATEST("repos/{owner}/{name}/releases/latest"),
    REPOSITORY_BRANCHES("repos/{owner}/{name}/branches"),

    ORGANISATION("orgs/{owner}"),
    ORGANISATION_REPOSITORIES("orgs/{owner}/repos"),

    STARS_REPOSITORIES("stars/{repositories}"),

    USER("users/{username}"),
    USER_REPOSITORIES("users/{username}/repos"),
    USER_QUERY("users/find/{query}"),

    ;

    fun format(vararg placeholders: Pair<String, Any> = arrayOf()): String {
        var path = "https://ungh.cc/$path"
        placeholders.forEach { (key, value) ->
            path = path.replace("{$key}", value.toString())
        }
        return path
    }

}