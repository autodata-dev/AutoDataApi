package com.autodata.api.uuids

import com.autodata.api.Column
import java.util.UUID

class UUIDColumn : Column<UUID> {
    override fun generate(): UUID {
        return UUID.randomUUID()
    }
}
