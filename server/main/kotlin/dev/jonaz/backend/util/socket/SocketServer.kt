package dev.jonaz.backend.util.socket

import com.corundumstudio.socketio.SocketIOServer
import dev.jonaz.backend.config.SocketConfiguration

object SocketServer {

    private var server: SocketIOServer

    init {
        val socketConfig = SocketConfiguration().get()
        server = SocketIOServer(socketConfig)
    }

    fun startAsync() {
        server.startAsync()
    }

    fun get(): SocketIOServer = server
}
