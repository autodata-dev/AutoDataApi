package com.autodata.api.columns

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

private data class LengthRange(val min: Int, val max: Int)

class StringColumnTests : DescribeSpec({
    describe("When generating a value") {
        describe("Meets minimum length") {
            withData(0, 1, 1024) { minLength ->
                val value = StringColumn(minLength).generate()

                value.length shouldBeGreaterThanOrEqual minLength
            }
        }

        describe("Meets maximum length") {
            withData(0, 1, 1024) { maxLength ->
                val value = StringColumn(maxLength = maxLength).generate()

                value.length shouldBeLessThanOrEqual maxLength
            }
        }

        describe("Meets range") {
            withData(
                LengthRange(0, 1),
                LengthRange(10, 100),
                LengthRange(100, 101),
            ) { (min, max) ->
                val value = StringColumn(min, max).generate()

                value.length shouldBeInRange min..max
            }
        }

        describe("Throws when the range is invalid") {
            withData(
                LengthRange(1, 0),
                LengthRange(100, 99),
                LengthRange(-2, -1),
                LengthRange(Int.MIN_VALUE, Int.MAX_VALUE),
            ) { (min, max) ->
                shouldThrow<IllegalArgumentException> {
                    StringColumn(min, max).generate()
                }
            }
        }
    }
})
