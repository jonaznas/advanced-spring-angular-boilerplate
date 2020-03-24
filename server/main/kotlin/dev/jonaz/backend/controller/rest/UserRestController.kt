package dev.jonaz.backend.controller.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRestController {

    @GetMapping("/")
    fun index(): Map<*, *> {
        return mapOf("success" to false)
    }
}
