package com.autodata.api

import java.io.Serializable

class Row(private val columns: List<Column<*>>) {
    val values: Sequence<Serializable>
        get() = sequence { columns.forEach { yield(it.generate()) } }
}
