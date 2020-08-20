package dev.jonaz.backend.component.auth

import dev.jonaz.backend.model.DatabaseTable
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class Login {
    private val table = DatabaseTable.User
    private val validateCredentials = ValidateCredentials()
    private val passwordEncoder = BCryptPasswordEncoder()

    fun validateCredentialsAndLogin(username: String?, password: String?): Pair<Boolean, String> {
        if (username.isNullOrBlank() ||
            password.isNullOrBlank()
        ) return Pair(false, "Invalid credentials")

        val isUsernameValid = validateCredentials.validateUsername(username).first
        val isPasswordValid = validateCredentials.validatePassword(password).first

        if (!isUsernameValid ||
            !isPasswordValid
        ) return Pair(false, "Invalid credentials")

        val user = transaction {
            table.select { table.username.eq(username) }.toList()
        }

        val valid = when (user.size) {
            1 -> passwordEncoder.matches(password, user[0][table.password])
            else -> false
        }

        return when (valid) {
            true -> {
                val userId = user[0][table.id]
                val role = user[0][table.role]

                val sessionToken = SessionManager.create(userId, role)
                Pair(true, sessionToken)
            }
            else -> Pair(false, "Invalid credentials")
        }
    }
}
