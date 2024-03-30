package com.autodata.api.decimals

@JvmInline
value class Scale(val value: Int = MAX_VALUE) {
    companion object {
        private const val MAX_VALUE = 38
    }

    init {
        if (value < 0) {
            throw IllegalArgumentException("Scale $value must be non-negative")
        }
        if (value > MAX_VALUE) {
            throw IllegalArgumentException("Scale $value must be less than or equal to $MAX_VALUE")
        }
    }
}
