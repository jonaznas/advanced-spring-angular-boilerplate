@file:Suppress("unused", "UNUSED_PARAMETER")

package dev.jonaz.backend.controller.sock

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.model.database.UserModel
import dev.jonaz.backend.util.session.SessionData
import dev.jonaz.backend.util.socket.SocketGuard
import dev.jonaz.backend.util.socket.SocketMapping
import dev.jonaz.backend.util.socket.SocketServer
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class UserSockController {

    @SocketMapping("/user/getAll", SocketGuard.ALLOW)
    fun userGetAll(client: SocketIOClient, dataMap: Map<*, *>, ackRequest: AckRequest, session: SessionData?) {
        data class User(val id: Int, val username: String)

        val users = transaction {
            UserModel.selectAll().map { User(it[UserModel.id], it[UserModel.username]) }
        }

        ackRequest.sendAckData(SocketServer.Resolve(true, users))
    }
}
