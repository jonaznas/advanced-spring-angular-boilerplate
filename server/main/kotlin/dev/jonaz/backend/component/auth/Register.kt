package dev.jonaz.backend.component.auth

import com.corundumstudio.socketio.SocketIOClient

class Register(private val client: SocketIOClient,
               private val data: Map<*, *>) {
    private val validateCredentials = ValidateCredentials()
    private val validateAccount = ValidateAccount()

    fun validateCredentialsAndCreateAccount(): Pair<Boolean, String> {
        val username = data["username"].toString()
        val password = data["password"].toString()
        val isUsernameValid = validateCredentials.validateUsername(username)
        val isPasswordValid = validateCredentials.validatePassword(password)
        val isAccAlreadyExist = validateAccount.isExist(username)

        when (false) {
            isUsernameValid.first -> return Pair(false, isUsernameValid.second ?: "")
            isPasswordValid.first -> return Pair(false, isPasswordValid.second ?: "")
            isAccAlreadyExist.first -> return Pair(false, isAccAlreadyExist.second ?: "")
        }

        return Pair(true, "not finished yet")
    }

}
