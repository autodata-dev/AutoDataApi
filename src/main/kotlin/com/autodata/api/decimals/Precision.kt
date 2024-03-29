package com.autodata.api.decimals

@JvmInline
value class Precision(val value: Int = Int.MAX_VALUE) {
    init {
        if (value < 1) {
            throw IllegalArgumentException("Precision $value must be greater than or equal to 1")
        }
    }
}
