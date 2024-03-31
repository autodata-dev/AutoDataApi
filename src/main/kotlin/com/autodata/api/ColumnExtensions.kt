package com.autodata.api

import com.autodata.api.decimals.DecimalColumn
import com.autodata.api.decimals.DecimalRange
import com.autodata.api.decimals.Scale
import com.autodata.api.integers.IntegerColumn
import com.autodata.api.integers.IntegerRange
import com.autodata.api.serials.SerialColumn
import com.autodata.api.strings.StringColumn
import com.autodata.api.strings.StringLength
import com.autodata.api.uuids.UUIDColumn
import com.netflix.dgs.codegen.generated.types.*

fun GenerateTableInput.getColumns(): List<Column<*>> {
    val columnPositionPairs = mutableListOf<Pair<Column<*>, Int>>()

    this.strings?.run { columnPositionPairs.addAll(getColumnPairs()) }
    this.integers?.run { columnPositionPairs.addAll(getColumnPairs()) }
    this.decimals?.run { columnPositionPairs.addAll(getColumnPairs()) }
    this.uuids?.run { columnPositionPairs.addAll(getColumnPairs()) }
    this.serials?.run { columnPositionPairs.addAll(getColumnPairs()) }

    return columnPositionPairs.sortedBy { it.second }.map { it.first }
}

@JvmName("getStringColumnPairs")
fun List<StringInput>.getColumnPairs(): List<Pair<StringColumn, Int>> {
    val pairs = mutableListOf<Pair<StringColumn, Int>>()

    this.forEach { input ->
        val length = StringLength(input.minLength, input.maxLength)
        val column = StringColumn(input.name, length)
        pairs.add(Pair(column, input.globalPosition ?: 0))
    }

    return pairs
}

@JvmName("getIntegerColumnPairs")
fun List<IntegerInput>.getColumnPairs(): List<Pair<IntegerColumn, Int>> {
    val pairs = mutableListOf<Pair<IntegerColumn, Int>>()

    this.forEach { input ->
        val range = IntegerRange(input.min, input.max)
        val column = IntegerColumn(input.name, range)
        pairs.add(Pair(column, input.globalPosition ?: 0))
    }

    return pairs
}

@JvmName("getDecimalColumnPairs")
fun List<DecimalInput>.getColumnPairs(): List<Pair<DecimalColumn, Int>> {
    val pairs = mutableListOf<Pair<DecimalColumn, Int>>()

    this.forEach { input ->
        val range = DecimalRange(input.min, input.max)
        val scale = Scale(input.scale)
        val column = DecimalColumn(input.name, range, scale)
        pairs.add(Pair(column, input.globalPosition ?: 0))
    }

    return pairs
}

@JvmName("getUUIDColumnPairs")
fun List<UUIDInput>.getColumnPairs(): List<Pair<UUIDColumn, Int>> {
    val pairs = mutableListOf<Pair<UUIDColumn, Int>>()

    this.forEach { input ->
        val column = UUIDColumn("name")
        pairs.add(Pair(column, input.globalPosition ?: 0))
    }

    return pairs
}

@JvmName("getSerialColumnPairs")
fun List<SerialInput>.getColumnPairs(): List<Pair<SerialColumn, Int>> {
    val pairs = mutableListOf<Pair<SerialColumn, Int>>()

    this.forEach { input ->
        val column = SerialColumn("name")
        pairs.add(Pair(column, input.globalPosition ?: 0))
    }

    return pairs
}
