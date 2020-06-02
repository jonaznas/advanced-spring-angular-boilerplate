package dev.jonaz.backend.config

import com.zaxxer.hikari.HikariConfig
import dev.jonaz.backend.util.docker.SecretManager
import dev.jonaz.backend.util.exposed.DatabaseValidator

class HikariConfiguration {
    private val secretManager = SecretManager()
    private val config = HikariConfig()

    private val dbHost: String? = System.getenv("database_host") ?: secretManager.getSecret("database_host")
    private val dbPort: String? = System.getenv("database_port") ?: secretManager.getSecret("database_port")
    private val dbName: String? = System.getenv("database_name") ?: secretManager.getSecret("database_name")
    private val dbUser: String? = System.getenv("database_user") ?: secretManager.getSecret("database_user")
    private val dbPass: String? = System.getenv("database_pass") ?: secretManager.getSecret("database_pass")

    init {
        DatabaseValidator().validateEnvironment()

        val url = "jdbc:postgresql://$dbHost:$dbPort/$dbName"
        Class.forName("org.postgresql.Driver")

        config.jdbcUrl = url
        config.username = dbUser
        config.password = dbPass
    }

    fun get(): HikariConfig = config
}
