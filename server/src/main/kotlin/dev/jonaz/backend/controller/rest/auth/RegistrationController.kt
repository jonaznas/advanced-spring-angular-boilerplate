package dev.jonaz.backend.controller.rest.auth

import dev.jonaz.backend.component.auth.Register
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth/register")
class RegistrationController {

    @PostMapping
    fun post(@RequestBody request: Request): Map<*, *> {
        val register = Register()

        val result = register.validateCredentialsAndCreateAccount(request.username, request.password)

        return mapOf("success" to result.first, "message" to result.second)
    }

    data class Request(
            val username: String?,
            val password: String?
    )
}
