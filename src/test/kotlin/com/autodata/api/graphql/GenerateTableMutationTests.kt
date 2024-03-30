package com.autodata.api.graphql

import com.autodata.api.TableDataFetcher
import com.netflix.dgs.codegen.generated.DgsClient
import com.netflix.dgs.codegen.generated.types.GenerateTableInput
import com.netflix.dgs.codegen.generated.types.MetadataInput
import com.netflix.dgs.codegen.generated.types.StringInput
import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ContextConfiguration
@SpringBootTest(classes = [DgsAutoConfiguration::class, TableDataFetcher::class])
class GenerateTableMutationTests(dgsQueryExecutor: DgsQueryExecutor) :
    DescribeSpec({
        describe("When successful") {
            it("Returns the url to the generated table") {
                val input =
                    GenerateTableInput(
                        metadata = MetadataInput(1000),
                        strings = listOf(StringInput("Column")),
                    )

                val mutation = DgsClient.buildMutation { generateTable(input) { resource } }

                val resource =
                    dgsQueryExecutor.executeAndExtractJsonPath<String>(
                        mutation,
                        "data.generateTable.resource",
                    )

                !resource.isNullOrEmpty() shouldBe true
            }
        }

        it("Generates the correct number of rows") {
            val numRows = 1000
            val input =
                GenerateTableInput(
                    metadata = MetadataInput(numRows),
                    strings = listOf(StringInput("Column"))
                )

            val mutation = DgsClient.buildMutation { generateTable(input) { rowCount } }

            val rowCount =
                dgsQueryExecutor.executeAndExtractJsonPath<Int>(
                    mutation,
                    "data.generateTable.rowCount",
                )

            rowCount shouldBe numRows
        }
    })
