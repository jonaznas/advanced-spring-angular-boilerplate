package dev.jonaz.backend.component.auth

class Login(val username: String, val password: String) {

    fun validateCredentialsAndAddSession(): Pair<Boolean, String> {
        return when (isValid()) {
            true -> {

                Pair(true, "")
            }
            else -> Pair(false, "")
        }
    }

    private fun isValid(): Boolean {
        return true
    }

}
