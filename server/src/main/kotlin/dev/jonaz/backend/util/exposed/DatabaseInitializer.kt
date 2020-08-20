package dev.jonaz.backend.util.exposed

import com.zaxxer.hikari.HikariDataSource
import dev.jonaz.backend.config.HikariSourceConfig
import dev.jonaz.backend.model.DatabaseTable
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

class DatabaseInitializer {
    private val dataSource: HikariDataSource

    init {
        val hikariConfig = HikariSourceConfig().get()
        dataSource = HikariDataSource(hikariConfig)
    }

    fun connect() = Database.connect(dataSource).let {
        this.createSchema()
    }

    private fun createSchema() = transaction {
        SchemaUtils.create(
                DatabaseTable.User,
                DatabaseTable.Session
        )
    }
}
