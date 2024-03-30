package com.autodata.api.decimals

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ScaleTests :
    DescribeSpec({
        describe("When created") {
            it("Must be greater than zero") { shouldThrow<IllegalArgumentException> { Scale(-1) } }

            it("Must be less than 38") { shouldThrow<IllegalArgumentException> { Scale(39) } }

            describe("Can be between 0 and 38") {
                withData(0..38) { value -> Scale(value).value shouldBe value }
            }
        }
    })
