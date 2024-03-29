package com.autodata.api.strings

import com.autodata.api.Column
import kotlin.random.Random

class StringColumn(private val length: StringLength = StringLength()) : Column<String> {
    companion object {
        private val DEFAULT_CHARACTERS = ('A'..'Z') + ('a'..'z')
    }

    override fun generate(): String {
        val stringBuilder = StringBuilder()
        val length = Random.nextInt(length.min, length.max + 1)

        while (stringBuilder.length < length) {
            stringBuilder.append(DEFAULT_CHARACTERS.random())
        }

        return stringBuilder.toString()
    }
}
