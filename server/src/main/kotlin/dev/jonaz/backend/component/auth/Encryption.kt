package dev.jonaz.backend.component.auth

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.MessageDigest
import java.security.SecureRandom

fun String.md5(): String {
    return hashString(this, "MD5")
}

fun String.sha256(): String {
    return hashString(this, "SHA-256")
}

fun String.bcrypt(): String {
    val encoder = BCryptPasswordEncoder(10, SecureRandom())
    return encoder.encode(this)
}

private fun hashString(input: String, algorithm: String): String {
    return MessageDigest
            .getInstance(algorithm)
            .digest(input.toByteArray())
            .fold("", { str, it -> str + "%02x".format(it) })
}
