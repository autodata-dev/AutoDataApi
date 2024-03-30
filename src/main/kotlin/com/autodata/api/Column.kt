package com.autodata.api

import java.io.Serializable

interface Column<T : Serializable> {
    fun generate(): T
}
