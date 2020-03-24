package dev.jonaz.backend.component.auth

import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.model.database.UsersModel
import dev.jonaz.backend.util.session.SessionManager
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class Login(private val client: SocketIOClient,
            private val data: Map<*, *>) {

    fun validateCredentialsAndAddSession(): Pair<Boolean, String> {
        val username = data["username"].toString()
        val password = data["password"].toString()

        val user = transaction {
            UsersModel.select { UsersModel.username.eq(username) and UsersModel.password.eq(password.sha256()) }.toList()
        }

        return when (1) {
            user.size -> {
                val userId = user[0][UsersModel.id]
                val sessionToken = SessionManager.create(userId, client)
                Pair(true, sessionToken)
            }
            else -> Pair(false, "Invalid credentials")
        }
    }
}
