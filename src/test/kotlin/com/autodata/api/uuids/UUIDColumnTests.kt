package com.autodata.api.uuids

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import java.util.HashSet
import java.util.UUID

class UUIDColumnTests :
    DescribeSpec({
        describe("When generating a value") {
            it("Every uuid is unique") {
                val column = UUIDColumn()
                val uuids = HashSet<UUID>()

                repeat(100) { uuids.add(column.generate()) }

                uuids.size shouldBe 100
            }
        }
    })