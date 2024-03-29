package com.autodata.api.decimals

@JvmInline
value class Scale(val value: Int = Int.MAX_VALUE) {
    init {
        if (value < 1) {
            throw IllegalArgumentException("Scale $value must be greater than or equal to 1")
        }
    }
}
