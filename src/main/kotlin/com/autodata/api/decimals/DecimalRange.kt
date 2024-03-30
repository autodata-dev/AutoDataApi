package com.autodata.api.decimals

class DecimalRange(val min: Double = -Double.MAX_VALUE, val max: Double = Double.MAX_VALUE) {
    init {
        if (min > max) {
            throw IllegalArgumentException(
                "Minimum value $min must be less than the maximum value $max"
            )
        }
    }
}
