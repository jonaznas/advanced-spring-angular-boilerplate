package dev.jonaz.backend.controller.rest.auth

import dev.jonaz.backend.component.auth.Login
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/login")
class LoginController {

    @PostMapping
    fun post(@RequestBody request: Request): Map<*, *> {
        val login = Login()

        val result = login.validateCredentialsAndLogin(request.username, request.password)

        return mapOf("success" to result.first, "message" to result.second)
    }

    data class Request(
            val username: String?,
            val password: String?
    )
}
