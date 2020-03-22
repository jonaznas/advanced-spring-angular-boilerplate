package dev.jonaz.backend.controller.sock

import dev.jonaz.backend.util.socket.SockMapping

class UserSockController {

    @SockMapping("/test")
    fun index(): String {
        println("test")
        return ""
    }

}
