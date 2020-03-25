package dev.jonaz.backend.util.exposed

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess

class DatabaseValidator {
    private val logger: Logger = LoggerFactory.getLogger(DatabaseInitializer::class.java)

    private val dbHost: String? = System.getenv("database.host")
    private val dbName: String? = System.getenv("database.name")
    private val dbUser: String? = System.getenv("database.user")
    private val dbPass: String? = System.getenv("database.pass")

    fun validateEnvironment() {
        val valuesExist = checkExist()

        if (!valuesExist.first) {
            logger.error("Environment variable \"${valuesExist.second}\" is undefined")
            exitProcess(0)
        }
    }

    private fun checkExist(): Pair<Boolean, String> {
        return when (null) {
            dbHost -> Pair(false, "database.host")
            dbName -> Pair(false, "database.name")
            dbUser -> Pair(false, "database.user")
            dbPass -> Pair(false, "database.pass")
            else -> Pair(true, "")
        }
    }
}
