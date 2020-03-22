package dev.jonaz.backend.config

import com.corundumstudio.socketio.AckMode
import com.corundumstudio.socketio.Configuration

class SocketConfiguration {
    private val config = Configuration()

    private val host = System.getenv("socket.host") ?: "0.0.0.0"
    private val port = System.getenv("socket.port") ?: 8443

    init {
        config.ackMode = AckMode.MANUAL
        config.workerThreads = 8
        config.bossThreads = 4
        config.hostname = host
        config.port = port as Int
    }

    fun get(): Configuration = config
}
