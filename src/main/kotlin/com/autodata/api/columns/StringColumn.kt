package com.autodata.api.columns

class StringColumn(minLength: Int? = null, maxLength: Int? = null) : Column<String> {
    companion object {
        private const val MAX_LENGTH = 1024
        private val DEFAULT_CHARACTERS = ('A'..'Z') + ('a'..'z')
    }

    private val minLength: Int = minLength ?: 0
    private val maxLength: Int = maxLength ?: MAX_LENGTH

    init {
        if (this.minLength < 0) {
            throw IllegalArgumentException("Minimum length $minLength must be non-negative")
        }
        if (this.minLength > MAX_LENGTH) {
            throw IllegalArgumentException("Minimum length $minLength cannot exceed $MAX_LENGTH")
        }
        if (this.maxLength < 0) {
            throw IllegalArgumentException("Maximum length $maxLength must be non-negative")
        }
        if (this.maxLength > MAX_LENGTH) {
            throw IllegalArgumentException("Maximum length $maxLength cannot exceed $MAX_LENGTH")
        }
        if (this.minLength > this.maxLength) {
            throw IllegalArgumentException("Minimum length $minLength must be less than or equal to max length $maxLength")
        }
    }

    override fun generate(): String {
        val stringBuilder = StringBuilder(minLength)
        val length = (minLength..maxLength).random()

        while (stringBuilder.length < length) {
            stringBuilder.append(DEFAULT_CHARACTERS.random())
        }

        return stringBuilder.toString()
    }
}
