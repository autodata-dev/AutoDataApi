package com.autodata.api.strings

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

private data class Values(val min: Int, val max: Int)

class StringLengthTests :
    DescribeSpec({
        describe("When created") {
            it("Minimum length must be non-negative") {
                shouldThrow<IllegalArgumentException> { StringLength(-1) }
            }

            it("Maximum length can be non-negative") {
                shouldThrow<IllegalArgumentException> { StringLength(max = -1) }
            }

            it("Minimum length must be less than or equal to the maximum") {
                shouldThrow<IllegalArgumentException> { StringLength(1, 0) }
            }

            it("Minimum length can equal the maximum length") {
                val length = StringLength(1, 1)

                length.min shouldBe 1
                length.max shouldBe 1
            }

            it("Maximum length must be less than 1024") {
                shouldThrow<IllegalArgumentException> { StringLength(max = 1025) }
            }

            it("Minimum length must be less than 1024") {
                shouldThrow<IllegalArgumentException> { StringLength(1025) }
            }

            describe("Range can be between 0 and 1024") {
                withData(Values(0, 1024), Values(1024, 1024), Values(0, 0)) { (min, max) ->
                    val length = StringLength(min, max)

                    length.min shouldBe min
                    length.max shouldBe max
                }
            }
        }
    })
