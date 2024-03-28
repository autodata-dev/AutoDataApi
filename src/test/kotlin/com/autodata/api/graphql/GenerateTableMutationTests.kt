package com.autodata.api.graphql

import com.autodata.api.TableDataFetcher
import com.netflix.dgs.codegen.generated.DgsClient
import com.netflix.dgs.codegen.generated.types.GenerateTableInput
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
                val input = GenerateTableInput(strings = listOf(StringInput("Column")))

                val mutation = DgsClient.buildMutation { generateTable(input) { resource } }

                val response =
                    dgsQueryExecutor.executeAndExtractJsonPath<String>(
                        mutation,
                        "data.generateTable.resource",
                    )

                !response.isNullOrEmpty() shouldBe true
            }
        }
    })
