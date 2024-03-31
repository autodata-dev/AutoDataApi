package com.autodata.api.uuids

import com.appmattus.kotlinfixture.config.range
import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.HashSet
import java.util.UUID

class UUIDColumnTests :
    DescribeSpec({
        val fixture = kotlinFixture()

        describe("When generating a value") {
            it("Every uuid is unique") {
                val column = UUIDColumn(fixture<String>())
                val uuids = HashSet<UUID>()
                val numUuids = fixture<Int> { factory { range(0..100) } }

                repeat(numUuids) { uuids.add(column.generate()) }

                uuids.size shouldBe numUuids
            }
        }
    })
