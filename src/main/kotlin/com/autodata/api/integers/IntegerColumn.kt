package com.autodata.api.integers

import com.autodata.api.Column
import kotlin.random.Random

class IntegerColumn(name: String, private val range: IntegerRange) : Column<Int>(name) {
    override fun generate(): Int {
        if (range.min == range.max) return range.min

        return Random.nextInt(range.min, range.max)
    }
}
