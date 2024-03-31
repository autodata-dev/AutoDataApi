package com.autodata.api

import com.netflix.dgs.codegen.generated.types.GenerateTableInput

class Table(private val input: GenerateTableInput) {
    private val _columns: List<Column<*>> = input.getColumns()
    val columns: List<ColumnInfo> = _columns.map { it.info }

    init {
        if (input.metadata.numRows < 0) {
            throw IllegalArgumentException(
                "Number of rows to generate ${input.metadata.numRows} must be non-negative"
            )
        }
    }

    fun generate(): Sequence<Row> = sequence { repeat(input.metadata.numRows) { yield(Row()) } }
}
