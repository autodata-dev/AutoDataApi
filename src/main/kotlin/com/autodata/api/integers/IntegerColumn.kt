package com.autodata.api.integers

import com.autodata.api.Column
import kotlin.random.Random

class IntegerColumn(private val range: IntegerRange) : Column<Int> {
    override fun generate(): Int {
        if (range.min == range.max) return range.min

        return Random.nextInt(range.min, range.max)
    }
}
