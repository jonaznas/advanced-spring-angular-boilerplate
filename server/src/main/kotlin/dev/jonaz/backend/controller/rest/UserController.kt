package dev.jonaz.backend.controller.rest

import org.springframework.security.access.annotation.Secured
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController {

    @CrossOrigin
    @PostMapping
    @Secured("ROLE_USER")
    fun post(authentication: Authentication): Map<String, Boolean> {
        return mapOf("test" to true)
    }
}
