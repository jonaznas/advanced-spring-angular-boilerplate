package dev.jonaz.backend.util.exposed

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class DatabaseValidator {
    private val logger: Logger = LoggerFactory.getLogger(DatabaseInitializer::class.java)

    private val dbHost: String? = System.getenv("database_host")
    private val dbName: String? = System.getenv("database_name")
    private val dbUser: String? = System.getenv("database_user")
    private val dbPass: String? = System.getenv("database_pass")

    fun validateEnvironment() {
        val valuesExist = checkExist()

        if (!valuesExist.first) {
            logger.error("Environment variable \"${valuesExist.second}\" is undefined")
            exitProcess(0)
        }
    }

    private fun checkExist(): Pair<Boolean, String> {
        return when (null) {
            dbHost -> Pair(false, "database_host")
            dbName -> Pair(false, "database_name")
            dbUser -> Pair(false, "database_user")
            dbPass -> Pair(false, "database_pass")
            else -> Pair(true, "")
        }
    }
}
