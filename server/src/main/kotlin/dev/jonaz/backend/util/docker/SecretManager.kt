package dev.jonaz.backend.util.docker

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.File

class SecretManager {
    private val logger: Logger = LoggerFactory.getLogger(SecretManager::class.java)

    fun getSecret(name: String): String? {
        var value: String? = null

        try {
            val reader = File("/run/secrets/${name}").bufferedReader()
            value = reader.readText()
        } catch (e: Exception) {
            logger.error("Cannot find docker secret $name")
        }

        return value
    }
}
