package dev.jonaz.backend.component.auth

import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.model.database.UserModel
import dev.jonaz.backend.util.session.SessionManager
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class Register(private val client: SocketIOClient) {

    private val validateCredentials = ValidateCredentials()
    private val validateAccount = ValidateAccount()

    fun validateCredentialsAndCreateAccount(username: String?, password: String?): Pair<Boolean, String?> {
        if (username.isNullOrEmpty()) return Pair(false, null)
        if (password.isNullOrEmpty()) return Pair(false, null)

        val isUsernameValid = validateCredentials.validateUsername(username)
        val isPasswordValid = validateCredentials.validatePassword(password)
        val isAccAlreadyExist = validateAccount.isExist(username)

        when (false) {
            isUsernameValid.first   -> return Pair(false, isUsernameValid.second)
            isPasswordValid.first   -> return Pair(false, isPasswordValid.second)
            isAccAlreadyExist.first -> return Pair(false, isAccAlreadyExist.second)
        }

        val userId = transaction {
            UserModel.insert {
                it[UserModel.username] = username
                it[UserModel.password] = password.bcrypt()
            } get UserModel.id
        }

        val sessionToken = SessionManager.create(userId, client)

        return Pair(true, sessionToken)
    }
}
