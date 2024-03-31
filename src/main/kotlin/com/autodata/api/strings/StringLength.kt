package com.autodata.api.strings

class StringLength(min: Int? = null, max: Int? = null) {
    val min = min ?: 0
    val max = max ?: MAX_LENGTH

    companion object {
        private const val MAX_LENGTH = 1024
    }

    init {
        if (this.min < 0) {
            throw IllegalArgumentException("Minimum length $min must be non-negative")
        }
        if (this.min > MAX_LENGTH) {
            throw IllegalArgumentException("Minimum length $min cannot exceed $MAX_LENGTH")
        }
        if (this.max < 0) {
            throw IllegalArgumentException("Maximum length $max must be non-negative")
        }
        if (this.max > MAX_LENGTH) {
            throw IllegalArgumentException("Maximum length $max cannot exceed $MAX_LENGTH")
        }
        if (this.min > this.max) {
            throw IllegalArgumentException(
                "Minimum length $min must be less than or equal to max length $min"
            )
        }
    }
}
