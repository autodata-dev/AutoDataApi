package com.autodata.api.strings

class StringColumn(minLength: Int? = null, maxLength: Int? = null) {
    companion object {
        private const val DEFAULT_MAX_LENGTH = 128
        private val DEFAULT_CHARACTERS = ('A'..'Z') + ('a'..'z')
    }

    private val minLength: Int = minLength ?: 0
    private val maxLength: Int = maxLength ?: DEFAULT_MAX_LENGTH.coerceAtLeast(this.minLength)

    init {
        if (this.minLength < 0) {
            throw IllegalArgumentException("Minimum length $minLength must be non-negative")
        }
        if (this.maxLength < 0) {
            throw IllegalArgumentException("Maximum length $maxLength must be non-negative")
        }
        if (this.minLength > this.maxLength) {
            throw IllegalArgumentException("Minimum length $minLength must be less than or equal to max length $maxLength")
        }
    }

    fun generate(): String {
        val stringBuilder = StringBuilder(minLength)
        val length = (minLength..maxLength).random()

        while (stringBuilder.length < length) {
            stringBuilder.append(DEFAULT_CHARACTERS.random())
        }

        return stringBuilder.toString()
    }
}
