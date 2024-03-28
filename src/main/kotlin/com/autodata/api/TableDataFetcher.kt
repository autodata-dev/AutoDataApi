package com.autodata.api

import com.netflix.dgs.codegen.generated.types.GenerateTableInput
import com.netflix.dgs.codegen.generated.types.GenerateTableResponse
import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument

@DgsComponent
class TableDataFetcher {
    @DgsMutation
    fun generateTable(
        @InputArgument input: GenerateTableInput,
    ): GenerateTableResponse {
        return GenerateTableResponse(
            code = { 200 },
            success = { true },
            message = { "Table generated successfully." },
            resource = { "https://www.generatedtableresource.com" },
            rowCount = { input.metadata.numRows },
        )
    }
}
