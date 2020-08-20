package dev.jonaz.backend.model.user

import java.time.LocalDateTime

data class SessionModel(
        val token: String,
        val user: Int,
        val meta: String,
        val role: String,
        val create: LocalDateTime,
        val expire: LocalDateTime
)
