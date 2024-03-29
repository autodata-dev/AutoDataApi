package com.autodata.api.columns

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

private data class ValueRange(val min: Int, val max: Int)

class IntegerColumnTests : DescribeSpec({
    describe("When generating a value") {
        describe("Meets the minimum") {
            withData(Int.MIN_VALUE, -1000, -1, 0, 1, 1000, Int.MAX_VALUE) { min ->
                val value = IntegerColumn(min = min).generate()

                value shouldBeGreaterThanOrEqual min
            }
        }

        describe("Meets the maximum") {
            withData(Int.MIN_VALUE, -1000, -1, 0, -1, 1000, Int.MAX_VALUE) { max ->
                val value = IntegerColumn(max = max).generate()

                value shouldBeLessThanOrEqual max
            }
        }

        describe("Meets the range") {
            withData(
                ValueRange(0, 1),
                ValueRange(-1, 0),
                ValueRange(0, 0),
                ValueRange(-1000, 1000),
                ValueRange(Int.MIN_VALUE, Int.MAX_VALUE),
            ) { (min, max) ->
                val value = IntegerColumn(min, max).generate()

                value shouldBeInRange min..max
            }
        }
    }
})
