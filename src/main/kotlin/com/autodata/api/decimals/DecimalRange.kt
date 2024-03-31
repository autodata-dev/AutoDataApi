package com.autodata.api.decimals

class DecimalRange(min: Double? = null, max: Double? = null) {
    val min = min ?: -Double.MAX_VALUE
    val max = max ?: Double.MAX_VALUE

    init {
        if (this.min > this.max) {
            throw IllegalArgumentException(
                "Minimum value $min must be less than the maximum value $max"
            )
        }
    }
}
