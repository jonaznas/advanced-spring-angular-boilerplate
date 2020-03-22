package dev.jonaz.backend.model

import dev.jonaz.backend.model.database.UsersModel
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseModelInitializer {
    init {
        transaction {
            SchemaUtils.create(
                    UsersModel
            )
        }
    }
}
