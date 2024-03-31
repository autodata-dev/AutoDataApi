package com.autodata.api.serials

import com.autodata.api.Column

class SerialColumn(name: String) : Column<Int>(name) {
    private var currentValue = 0

    override fun generate(): Int {
        currentValue++
        return currentValue
    }
}
