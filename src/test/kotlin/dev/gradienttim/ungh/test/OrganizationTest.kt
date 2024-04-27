package dev.gradienttim.ungh.test

import dev.gradienttim.ungh.Ungh
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldNotBe

class OrganizationTest : FunSpec({

    test("organization information") {
        val organization = Ungh.organization("unjs")

        organization shouldNotBe null
    }

    test("organization repositories") {
        val repositories = Ungh.organizationRepositories("unjs")

        repositories.size shouldBeGreaterThan 0
    }

})