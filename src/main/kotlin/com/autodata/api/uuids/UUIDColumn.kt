package com.autodata.api.uuids

import com.autodata.api.Column
import java.util.UUID

class UUIDColumn(name: String) : Column<UUID>(name) {
    override fun generate(): UUID {
        return UUID.randomUUID()
    }
}
