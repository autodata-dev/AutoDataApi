package com.autodata.api.columns

class IntegerColumn(min: Int? = null, max: Int? = null) : Column<Int> {
    private val min = min ?: Int.MIN_VALUE
    private val max = max ?: Int.MAX_VALUE

    init {
        if (this.min > this.max) {
            throw IllegalArgumentException("Minimum value $min must be less than or equal to maximum value $max")
        }
    }

    override fun generate(): Int {
        return (min..max).random()
    }
}
