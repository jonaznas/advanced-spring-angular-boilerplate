package dev.jonaz.backend.component.auth

import dev.jonaz.backend.model.DatabaseTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ValidateAccount {
    private val table = DatabaseTable.User

    fun isExist(username: String): Pair<Boolean, String?> {
        val user = transaction {
            table.select { table.username.eq(username) }.toList()
        }

        return when(0) {
            user.size -> Pair(true, "")
            else -> Pair(false, "This username is already taken")
        }
    }
}
