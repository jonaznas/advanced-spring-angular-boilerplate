package dev.jonaz.backend.util.exposed

import dev.jonaz.backend.util.docker.SecretManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class DatabaseValidator {
    private val logger: Logger = LoggerFactory.getLogger(DatabaseInitializer::class.java)
    private val secretManager = SecretManager()

    private val dbHost: String? = System.getenv("database_host") ?: secretManager.getSecret("database_host")
    private val dbPort: String? = System.getenv("database_port") ?: secretManager.getSecret("database_port")
    private val dbName: String? = System.getenv("database_name") ?: secretManager.getSecret("database_name")
    private val dbUser: String? = System.getenv("database_user") ?: secretManager.getSecret("database_user")
    private val dbPass: String? = System.getenv("database_pass") ?: secretManager.getSecret("database_pass")

    fun validateEnvironment() {
        val valuesExist = checkExist()

        if (!valuesExist.first) {
            logger.error(" ")
            logger.error("----------------------------------------")
            logger.error("Variable \"${valuesExist.second}\" is undefined")
            logger.error("----------------------------------------")
            logger.error(" ")
        }
    }

    private fun checkExist(): Pair<Boolean, String> {
        return when (null) {
            dbHost -> Pair(false, "database_host")
            dbPort -> Pair(false, "database_port")
            dbName -> Pair(false, "database_name")
            dbUser -> Pair(false, "database_user")
            dbPass -> Pair(false, "database_pass")
            else   -> Pair(true, "")
        }
    }
}
