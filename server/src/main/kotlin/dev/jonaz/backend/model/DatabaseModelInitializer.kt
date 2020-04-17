package dev.jonaz.backend.model

import dev.jonaz.backend.model.database.UserModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseModelInitializer {

    fun createSchema() = transaction {
        SchemaUtils.create(
                UserModel
        )
    }
}
