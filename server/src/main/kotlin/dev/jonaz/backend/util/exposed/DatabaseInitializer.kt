package dev.jonaz.backend.util.exposed

import com.zaxxer.hikari.HikariDataSource
import dev.jonaz.backend.config.HikariConfiguration
import org.jetbrains.exposed.sql.Database

class DatabaseInitializer {
    private val dataSource: HikariDataSource

    init {
        val hikariConfig = HikariConfiguration().get()
        dataSource = HikariDataSource(hikariConfig)
    }

    fun connect() = Database.connect(dataSource)
}
