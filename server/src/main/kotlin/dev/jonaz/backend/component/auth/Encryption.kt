package dev.jonaz.backend.component.auth

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.SecureRandom

fun String.bcrypt(): String {
    val encoder = BCryptPasswordEncoder(10, SecureRandom())
    return encoder.encode(this)
}
