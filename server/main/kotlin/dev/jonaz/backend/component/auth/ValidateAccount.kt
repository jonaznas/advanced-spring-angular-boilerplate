package dev.jonaz.backend.component.auth

import dev.jonaz.backend.model.database.UsersModel
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class ValidateAccount {

    fun isExist(username: String): Pair<Boolean, String?> {
        val user = transaction {
            UsersModel.select { UsersModel.username.eq(username) }.toList()
        }

        return when(0) {
            user.size -> Pair(true, "")
            else -> Pair(false, "This username is already taken")
        }
    }
}
