package com.autodata.api.columns

interface Column<T> {
    fun generate(): T
}
