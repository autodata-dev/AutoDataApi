package com.autodata.api.decimals

import com.autodata.api.Column
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class DecimalColumn(
    name: String,
    private val range: DecimalRange = DecimalRange(),
    private val scale: Scale = Scale()
) : Column<BigDecimal>(name) {

    override fun generate(): BigDecimal {
        if (range.min == range.max) return range.min.toBigDecimal()

        val double = Random.nextDouble(range.min, range.max)
        val value = BigDecimal(double)
        return value.setScale(scale.value, RoundingMode.HALF_UP)
    }
}
