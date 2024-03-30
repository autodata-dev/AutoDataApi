package com.autodata.api.integers

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

private data class Values(val min: Int, val max: Int)

class IntegerRangeTests :
    DescribeSpec({
        describe("When created") {
            it("Minimum value must be less than or equal to the maximum") {
                shouldThrow<IllegalArgumentException> { IntegerRange(1, 0) }
            }

            it("Minimum can be equal to the maximum") {
                val range = IntegerRange(1, 1)

                range.min shouldBe 1
                range.max shouldBe 1
            }

            it("Minimum can be less than the maximum") {
                val range = IntegerRange(0, 1)

                range.min shouldBe 0
                range.max shouldBe 1
            }

            describe("Range can reach the maximum and minimum integer values") {
                withData(
                    Values(Int.MIN_VALUE, Int.MIN_VALUE),
                    Values(Int.MIN_VALUE, Int.MAX_VALUE),
                    Values(Int.MAX_VALUE, Int.MAX_VALUE)
                ) { (min, max) ->
                    val range = IntegerRange(min, max)

                    range.min shouldBe min
                    range.max shouldBe max
                }
            }
        }
    })
