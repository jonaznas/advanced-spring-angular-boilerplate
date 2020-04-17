package dev.jonaz.backend.component.auth

import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.model.database.UserModel
import dev.jonaz.backend.util.session.SessionManager
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class Login(private val client: SocketIOClient) {
    fun validateCredentialsAndAddSession(username: String?, password: String?): Pair<Boolean, String> {
        if (username.isNullOrBlank()) return Pair(false, "Invalid credentials")
        if (password.isNullOrBlank()) return Pair(false, "Invalid credentials")

        val user = transaction {
            UserModel.select { UserModel.username.eq(username) and UserModel.password.eq(password.sha256()) }.toList()
        }

        return when (user.size) {
            1    -> {
                val userId = user[0][UserModel.id]
                val sessionToken = SessionManager.create(userId, client)
                Pair(true, sessionToken)
            }
            else -> Pair(false, "Invalid credentials")
        }
    }
}
