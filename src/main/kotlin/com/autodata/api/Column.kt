package com.autodata.api

interface Column<T> {
    fun generate(): T
}
