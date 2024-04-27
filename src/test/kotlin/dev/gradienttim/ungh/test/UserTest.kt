package dev.gradienttim.ungh.test

import dev.gradienttim.ungh.Ungh
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class UserTest : FunSpec({

    test("user information") {
        val user = Ungh.user("GradientTim")

        user shouldNotBe null
        user?.id shouldBe 54452102
    }

    test("user repositories") {
        val repositories = Ungh.userRepositories("GradientTim")

        repositories.size shouldBeGreaterThan 0
    }

    test("user query") {
        val query = Ungh.userQuery("pooya@pi0.io")

        query shouldNotBe null
        query?.id shouldBe 5158436
    }

})