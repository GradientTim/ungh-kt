package dev.gradienttim.ungh.test

import dev.gradienttim.ungh.Ungh
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldNotBe

class StarsTest : FunSpec({

    test("repository stars") {
        val stars = Ungh.stars(listOf(
            "unjs/ungh",
            "unjs/nitro",
        ))

        stars shouldNotBe null
        stars?.totalStars shouldNotBe 0
    }

    test("single repository stars") {
        val stars = Ungh.stars("Jetbrains/Kotlin")

        stars shouldNotBe null
        stars?.totalStars shouldNotBe 0
    }

})