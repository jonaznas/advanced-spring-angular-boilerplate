package dev.jonaz.backend.controller.rest

import dev.jonaz.backend.model.rest.IndexResolve
import dev.jonaz.backend.util.socket.SocketServer
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserRestController {

    @GetMapping("/")
    fun index(): IndexResolve {
        return IndexResolve(true, "Welcome to the rest server")
    }
}
