package com.autodata.api.serials

import com.appmattus.kotlinfixture.config.range
import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class SerialColumnTests :
    DescribeSpec({
        val fixture = kotlinFixture()

        describe("When generating a value") {
            it("Generates values sequentially") {
                val column = SerialColumn(fixture<String>())
                val values = mutableListOf<Int>()
                val numSerials = fixture<Int> { factory { range(1..100) } }

                repeat(numSerials) { values.add(column.generate()) }

                values shouldBe (1..numSerials).toList()
            }
        }
    })
