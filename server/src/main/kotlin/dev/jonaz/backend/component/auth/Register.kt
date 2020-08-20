package dev.jonaz.backend.component.auth

import dev.jonaz.backend.model.DatabaseTable
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class Register {
    private val table = DatabaseTable.User
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
            table.insert {
                it[table.username] = username
                it[table.password] = password.bcrypt()
            } get table.id
        }

        return Pair(true, "xxx")
    }
}
