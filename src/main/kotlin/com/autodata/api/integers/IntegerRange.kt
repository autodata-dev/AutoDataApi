package com.autodata.api.integers

class IntegerRange(val min: Int = Int.MIN_VALUE, val max: Int = Int.MAX_VALUE) {
    init {
        if (min > max) {
            throw IllegalArgumentException(
                "Minimum value $min must be less than or equal to maximum value $max"
            )
        }
    }
}
