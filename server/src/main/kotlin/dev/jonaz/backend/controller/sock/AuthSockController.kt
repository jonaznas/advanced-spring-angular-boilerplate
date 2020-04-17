@file:Suppress("unused", "UNUSED_PARAMETER")

package dev.jonaz.backend.controller.sock

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.component.auth.Login
import dev.jonaz.backend.component.auth.Register
import dev.jonaz.backend.util.session.SessionData
import dev.jonaz.backend.util.session.SessionManager
import dev.jonaz.backend.util.socket.SocketData
import dev.jonaz.backend.util.socket.SocketGuard
import dev.jonaz.backend.util.socket.SocketMapping
import dev.jonaz.backend.util.socket.SocketServer

class AuthSockController {

    @SocketMapping("/auth/login", SocketGuard.ALLOW)
    fun authLogin(client: SocketIOClient, dataMap: Map<*, *>, ackRequest: AckRequest, session: SessionData?) {
        val login = Login(client)
        val data = SocketData.map<AuthLoginData>(dataMap)

        val result = login.validateCredentialsAndAddSession(data.username, data.password)

        ackRequest.sendAckData(SocketServer.Resolve(result.first, result.second))
    }

    @SocketMapping("/auth/validate", SocketGuard.ALLOW)
    fun authValidate(client: SocketIOClient, dataMap: Map<*, *>, ackRequest: AckRequest, session: SessionData?) {
        val valid = SessionManager.validate(session?.token, client)

        ackRequest.sendAckData(SocketServer.Resolve(valid))
    }

    @SocketMapping("/auth/register", SocketGuard.ALLOW)
    fun authTest(client: SocketIOClient, dataMap: Map<*, *>, ackRequest: AckRequest, session: SessionData?) {
        val register = Register(client)
        val data = SocketData.map<AuthRegisterData>(dataMap)

        val result = register.validateCredentialsAndCreateAccount(data.username, data.password)

        ackRequest.sendAckData(SocketServer.Resolve(result.first, result.second))
    }

    data class AuthLoginData(
            val username: String? = null,
            val password: String? = null
    )

    data class AuthRegisterData(
            val username: String? = null,
            val password: String? = null
    )
}
