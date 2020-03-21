package dev.jonaz.backend.util.exposed

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

class DatabaseInitializer {
    private val dataSource: HikariDataSource

    private val dbHost: String? = System.getenv("database.host")
    private val dbPort: String? = System.getenv("database.port") ?: "5432"
    private val dbName: String? = System.getenv("database.name")
    private val dbUser: String? = System.getenv("database.user")
    private val dbPass: String? = System.getenv("database.pass")

    init {
        DatabaseValidator()
                .validateEnvironment()

        val url = "jdbc:postgresql://$dbHost:$dbPort/$dbName"
        Class.forName("org.postgresql.Driver")
        val cfg = HikariConfig()
        cfg.jdbcUrl = url
        cfg.username = dbUser
        cfg.password = dbPass
        dataSource = HikariDataSource(cfg)
        Database.connect(dataSource)
    }
}
