package com.autodata.api.strings

import com.autodata.api.Column
import kotlin.random.Random

class StringColumn(name: String, private val length: StringLength = StringLength()) :
    Column<String>(name) {
    companion object {
        private val DEFAULT_CHARACTERS = ('A'..'Z') + ('a'..'z')
    }

    override fun generate(): String {
        val stringBuilder = StringBuilder()
        val length =
            if (length.min == length.max) {
                length.min
            } else {
                Random.nextInt(length.min, length.max)
            }

        while (stringBuilder.length < length) {
            stringBuilder.append(DEFAULT_CHARACTERS.random())
        }

        return stringBuilder.toString()
    }
}
