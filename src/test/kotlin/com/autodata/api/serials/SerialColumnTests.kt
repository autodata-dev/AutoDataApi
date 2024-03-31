package com.autodata.api.serials

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SerialColumnTests :
    DescribeSpec({
        describe("When generating a value") {
            it("Generates values sequentially") {
                val column = SerialColumn("name")
                val values = mutableListOf<Int>()

                repeat(100) { values.add(column.generate()) }

                values shouldBe (1..100).toList()
            }
        }
    })
