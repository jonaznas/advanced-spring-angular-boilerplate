package dev.jonaz.backend.util.socket

import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.util.session.SessionManager

enum class SocketGuard {
    ALLOW {
        override fun validateAuthority(sessionToken: String, client: SocketIOClient): Boolean = true
    },

    DENY {
        override fun validateAuthority(sessionToken: String, client: SocketIOClient): Boolean = false
    },

    USER {
        override fun validateAuthority(sessionToken: String, client: SocketIOClient): Boolean = SessionManager.validate(sessionToken, client)
    };

    abstract fun validateAuthority(sessionToken: String, client: SocketIOClient): Boolean
}
