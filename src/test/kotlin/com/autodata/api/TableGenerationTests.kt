package com.autodata.api

import com.netflix.dgs.codegen.generated.types.*
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class TableGenerationTests :
    DescribeSpec({
        describe("When generating") {
            it("Generates the correct column names from an input") {
                val metadata = MetadataInput(1)
                val expectedColumnName = "Column A"
                val strings = listOf(StringInput(expectedColumnName))
                val input = GenerateTableInput(metadata, strings)

                val sut = Table(input)

                sut.columns[0].name shouldBe expectedColumnName
            }

            it("Generates columns in the correct order") {
                val metadata = MetadataInput(1)
                val strings = listOf(StringInput("string", globalPosition = 1))
                val decimals = listOf(DecimalInput("decimal", globalPosition = 0))
                val input = GenerateTableInput(metadata, strings, decimals = decimals)

                val sut = Table(input)

                sut.columns[0].name shouldBe "decimal"
                sut.columns[1].name shouldBe "string"
            }

            describe("Generates the correct number of rows") {
                withData(0, 1, 10) { numRows ->
                    val metadata = MetadataInput(numRows)
                    val integers = listOf(IntegerInput("name"))
                    val input = GenerateTableInput(metadata, integers = integers)
                    val sut = Table(input)

                    sut.generate().count() shouldBe numRows
                }
            }

            it("Throws when the number of rows to generate is negative") {
                shouldThrow<IllegalArgumentException> {
                    val metadata = MetadataInput(-1)
                    val integers = listOf(IntegerInput("name"))
                    val input = GenerateTableInput(metadata, integers = integers)

                    Table(input)
                }
            }

            it("Generates rows with correct values") {
                val metadata = MetadataInput(10)
                val serials = listOf(SerialInput("B", globalPosition = 1))
                val input = GenerateTableInput(metadata, serials = serials)
                val sut = Table(input)

                sut.generate().forEachIndexed { i, row -> row.values.toList()[0] shouldBe i + 1 }
            }
        }
    })
