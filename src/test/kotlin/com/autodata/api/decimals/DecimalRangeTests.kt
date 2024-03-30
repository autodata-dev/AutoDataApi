package com.autodata.api.decimals

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

private data class Values(val min: Double, val max: Double)

class DecimalRangeTests :
    DescribeSpec({
        describe("When created") {
            it("Minimum must be less than or equal to the maximum") {
                shouldThrow<IllegalArgumentException> { DecimalRange(1.0, 0.0) }
            }

            it("Minimum can be equal to the maximum") {
                val range = DecimalRange(1.0, 1.0)

                range.min shouldBe 1.0
                range.max shouldBe 1.0
            }

            it("Minimum can be less than the maximum") {
                val range = DecimalRange(0.0, 1.0)

                range.min shouldBe 0.0
                range.max shouldBe 1.0
            }

            describe("Range can reach the maximum and minimum double values") {
                withData(
                    Values(-Double.MAX_VALUE, Double.MAX_VALUE),
                    Values(-Double.MAX_VALUE, Double.MAX_VALUE),
                    Values(Double.MAX_VALUE, Double.MAX_VALUE)
                ) { (min, max) ->
                    val range = DecimalRange(min, max)

                    range.min shouldBe min
                    range.max shouldBe max
                }
            }
        }
    })
