package dev.jonaz.backend.util.session

import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.util.socket.SocketServer
import java.util.*
import kotlin.streams.asSequence

object SessionManager {
    private val server = SocketServer.get()
    private var storage: MutableMap<String, Map<String, Any>> = mutableMapOf()

    private fun deleteSessions(keys: Map<String, Map<String, Any>>) = keys.forEach { (t, _) -> storage.keys.remove(t) }

    private fun getById(userid: Int): Map<String, Map<String, Any>> {
        var keys = storage.filter { it.value["userid"] == userid }
        if (keys.keys.size > 1) {
            deleteSessions(keys)
            keys = mapOf()
        }
        return keys
    }

    private fun generateToken(length: Long = 60): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.:,-#"
        return Random().ints(length, 0, source.length)
                .asSequence()
                .map(source::get)
                .joinToString("")
    }

    private fun exist(token: String): Boolean = storage.containsKey(token)

    fun get(token: String): Map<String, Any>? = storage[token]

    fun create(userid: Int, client: SocketIOClient): String {
        val newToken = generateToken()
        val oldSessions = getById(userid)

        deleteSessions(oldSessions)

        storage[newToken] = mapOf(
                "userid" to userid,
                "remoteAddress" to client.handshakeData.address.address
        )

        kickAll(userid)
        client.joinRoom("user-$userid")

        return newToken
    }

    fun validate(token: String, client: SocketIOClient): Boolean {
        return if (exist(token)) {
            val session = get(token)
            val remoteAddress = session?.get("remoteAddress")

            if (remoteAddress != client.handshakeData.address.address) return false

            true
        } else false
    }

    fun destroy(userid: Int) {
        val keys = getById(userid)
        kickAll(userid)
        deleteSessions(keys)
    }

    private fun kickAll(userid: Int) = server.getRoomOperations("client-$userid").clients.forEach { client: SocketIOClient ->
        client.leaveRoom("client-$userid")
    }
}
