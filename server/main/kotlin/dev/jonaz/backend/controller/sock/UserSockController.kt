package dev.jonaz.backend.controller.sock

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.util.socket.SockMapping

class UserSockController {

    @SockMapping("/test")
    fun index(client: SocketIOClient, data: Map<*, *>, ackRequest: AckRequest): String {
        println("test")
        ackRequest.sendAckData(mapOf("hey" to "ok"))
        return ""
    }

    @SockMapping("/abc")
    fun index2(client: SocketIOClient, data: Map<*, *>, ackRequest: AckRequest): String {
        println("test2")
        return ""
    }

}
