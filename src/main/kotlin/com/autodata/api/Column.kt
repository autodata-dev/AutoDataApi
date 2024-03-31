package com.autodata.api

import java.io.Serializable

abstract class Column<out T : Serializable>(name: String) {
    val info: ColumnInfo = ColumnInfo(name)

    abstract fun generate(): T
}
