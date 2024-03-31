package com.autodata.api.decimals

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.bigdecimal.shouldBeGreaterThanOrEquals
import io.kotest.matchers.bigdecimal.shouldBeInRange
import io.kotest.matchers.bigdecimal.shouldBeLessThanOrEquals
import io.kotest.matchers.shouldBe

class DecimalColumnTests :
    DescribeSpec({
        val fixture = kotlinFixture()

        describe("When generating a value") {
            describe("Meets the minimum") {
                withData(
                    DecimalRange(-Double.MAX_VALUE),
                    DecimalRange(-1000.0),
                    DecimalRange(-1.0),
                    DecimalRange(0.0),
                    DecimalRange(Double.MIN_VALUE),
                    DecimalRange(1.0),
                    DecimalRange(1000.0),
                    DecimalRange(Double.MAX_VALUE)
                ) { range ->
                    val value = DecimalColumn(fixture<String>(), range).generate()

                    value.shouldBeGreaterThanOrEquals(range.min.toBigDecimal())
                }
            }

            describe("Meets the maximum") {
                withData(
                    DecimalRange(max = -Double.MAX_VALUE),
                    DecimalRange(max = -1000.0),
                    DecimalRange(max = -1.0),
                    DecimalRange(max = 0.0),
                    DecimalRange(max = Double.MIN_VALUE),
                    DecimalRange(max = -1.0),
                    DecimalRange(max = 1000.0),
                    DecimalRange(max = Double.MAX_VALUE)
                ) { range ->
                    val value = DecimalColumn(fixture<String>(), range).generate()

                    value.shouldBeLessThanOrEquals(range.max.toBigDecimal())
                }
            }

            describe("Meets the range") {
                withData(
                    DecimalRange(0.0, 0.0),
                    DecimalRange(-Double.MAX_VALUE, Double.MAX_VALUE),
                    DecimalRange(-Double.MAX_VALUE, -Double.MAX_VALUE),
                    DecimalRange(Double.MAX_VALUE, Double.MAX_VALUE),
                    DecimalRange(-1.0, 1.0)
                ) { range ->
                    val value = DecimalColumn(fixture<String>(), range).generate()

                    value.shouldBeInRange(range.min.toBigDecimal()..range.max.toBigDecimal())
                }
            }

            describe("Meets the scale") {
                withData(Scale(0), Scale(1), Scale(10), Scale(38)) { scale ->
                    val value = DecimalColumn(fixture<String>(), scale = scale).generate()

                    value.scale() shouldBe scale.value
                }
            }
        }
    })
