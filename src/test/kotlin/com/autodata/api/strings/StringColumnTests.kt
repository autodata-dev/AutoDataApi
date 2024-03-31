package com.autodata.api.strings

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.ints.shouldBeGreaterThanOrEqual
import io.kotest.matchers.ints.shouldBeInRange
import io.kotest.matchers.ints.shouldBeLessThanOrEqual

class StringColumnTests :
    DescribeSpec({
        describe("When generating a value") {
            describe("Meets minimum length") {
                withData(StringLength(0), StringLength(1), StringLength(1024)) { length ->
                    val value = StringColumn("name", length).generate()

                    value.length shouldBeGreaterThanOrEqual length.min
                }
            }

            describe("Meets maximum length") {
                withData(StringLength(max = 0), StringLength(max = 1), StringLength(max = 1024)) {
                    length ->
                    val value = StringColumn("name", length).generate()

                    value.length shouldBeLessThanOrEqual length.max
                }
            }

            describe("Meets range") {
                withData(
                    StringLength(0, 0),
                    StringLength(0, 1),
                    StringLength(10, 100),
                    StringLength(100, 101),
                    StringLength(1024, 1024)
                ) { length ->
                    val value = StringColumn("name", length).generate()

                    value.length shouldBeInRange length.min..length.max
                }
            }
        }
    })
