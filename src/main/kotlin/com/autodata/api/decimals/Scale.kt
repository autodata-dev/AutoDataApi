package com.autodata.api.decimals

class Scale(value: Int? = null) {
    val value = value ?: MAX_VALUE

    companion object {
        private const val MAX_VALUE = 38
    }

    init {
        if (this.value < 0) {
            throw IllegalArgumentException("Scale $value must be non-negative")
        }
        if (this.value > MAX_VALUE) {
            throw IllegalArgumentException("Scale $value must be less than or equal to $MAX_VALUE")
        }
    }
}
