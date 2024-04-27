package dev.gradienttim.ungh.test

import dev.gradienttim.ungh.Ungh
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class RepositoryTest : FunSpec({

    test("repository information") {
        val repository = Ungh.repository("unjs", "ungh")

        repository shouldNotBe null
        repository?.repo shouldBe "unjs/ungh"
        repository?.name shouldBe "ungh"
        repository?.defaultBranch shouldBe "main"
    }

    test("repository contributors") {
        val contributors = Ungh.repositoryContributors("unjs", "ungh")
        val renovateContributor = contributors.firstOrNull { it.username == "renovate[bot]" }

        contributors.size shouldNotBe 0
        renovateContributor shouldNotBe null
        renovateContributor?.id shouldBe 29139614
    }

    test("repository branch files") {
        val branchFiles = Ungh.repositoryBranchFiles("unjs", "ungh", "main")

        branchFiles shouldNotBe null
        branchFiles?.files?.size shouldNotBe 0
    }

    test("repository branch file") {
        val branchFile = Ungh.repositoryBranchFile("unjs", "ungh", "main", "README.md")

        branchFile shouldNotBe null
    }

    test("repository readme") {
        val readMe = Ungh.repositoryReadMe("unjs", "ungh")

        readMe shouldNotBe null
    }

    test("repository releases") {
        val releases = Ungh.repositoryReleases("JetBrains", "Kotlin")

        releases.size shouldBeGreaterThan 0
    }

    test("repository latest release") {
        val latestRelease = Ungh.repositoryLatestRelease("JetBrains", "Kotlin")

        latestRelease shouldNotBe null
    }

    test("repository branches") {
        val branches = Ungh.repositoryBranches("unjs", "ungh")
        val mainBranch = branches.firstOrNull { it.name == "main" }

        branches.size shouldBeGreaterThan 0
        mainBranch shouldNotBe null
    }

})