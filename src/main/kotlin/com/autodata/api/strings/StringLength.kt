package com.autodata.api.strings

class StringLength(val min: Int = 0, val max: Int = MAX_LENGTH) {
    companion object {
        private const val MAX_LENGTH = 1024
    }

    init {
        if (min < 0) {
            throw IllegalArgumentException("Minimum length $min must be non-negative")
        }
        if (min > MAX_LENGTH) {
            throw IllegalArgumentException("Minimum length $min cannot exceed $MAX_LENGTH")
        }
        if (max < 0) {
            throw IllegalArgumentException("Maximum length $max must be non-negative")
        }
        if (max > MAX_LENGTH) {
            throw IllegalArgumentException("Maximum length $max cannot exceed $MAX_LENGTH")
        }
        if (min > max) {
            throw IllegalArgumentException(
                "Minimum length $min must be less than or equal to max length $min"
            )
        }
    }
}
