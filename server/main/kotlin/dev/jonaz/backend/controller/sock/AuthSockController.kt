@file:Suppress("unused")

package dev.jonaz.backend.controller.sock

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.component.auth.Login
import dev.jonaz.backend.component.auth.Register
import dev.jonaz.backend.util.session.SessionManager
import dev.jonaz.backend.util.socket.SocketMapping
import dev.jonaz.backend.util.socket.SocketGuard

class AuthSockController {

    @SocketMapping("/auth/login", SocketGuard.ALLOW)
    fun authLogin(client: SocketIOClient, data: Map<*, *>, ackRequest: AckRequest, sessionToken: String) {
        val login = Login(client, data)
        val result = login.validateCredentialsAndAddSession()
        ackRequest.sendAckData(mapOf("success" to result.first, "message" to result.second))
    }

    @SocketMapping("/auth/validate", SocketGuard.ALLOW)
    fun authValidate(client: SocketIOClient, data: Map<*, *>, ackRequest: AckRequest, sessionToken: String) {
        val res = SessionManager.validate(sessionToken, client)
        ackRequest.sendAckData(mapOf("valid" to res))
    }

    @SocketMapping("/auth/register", SocketGuard.ALLOW)
    fun authTest(client: SocketIOClient, data: Map<*, *>, ackRequest: AckRequest, sessionToken: String) {
        val register = Register(client, data)
        val result = register.validateCredentialsAndCreateAccount()
        ackRequest.sendAckData(mapOf("success" to result.first, "message" to result.second))
    }
}
