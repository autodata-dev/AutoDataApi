package com.autodata.api.decimals

import com.autodata.api.Column
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

class DecimalColumn(
    private val range: DecimalRange = DecimalRange(),
    private val scale: Scale = Scale(),
    private val precision: Precision = Precision()
) : Column<BigDecimal> {

    override fun generate(): BigDecimal {
        if (range.min == range.max) return range.min.toBigDecimal()

        val double = Random.nextDouble(range.min, range.max)
        val value = BigDecimal(double)
        value.setScale(scale.value + (precision.value - value.precision()), RoundingMode.HALF_UP)
        return value
    }
}
