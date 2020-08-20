package dev.jonaz.backend.model

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object DatabaseTable {
    object User : Table("users") {
        val id = integer("id").autoIncrement()

        val username = text("username")
        val password = text("password")
        val role = text("role").default("ROLE_USER")
        val createdAt = datetime("created").default(LocalDateTime.now())

        override val primaryKey = PrimaryKey(id, name = "id")
    }

    object Session : Table("sessions") {
        val token = text("token")

        val user = integer("user")
        val meta = text("meta")
        val role = varchar("role", 256)
        val created = datetime("created")
        val expire = datetime("expire")

        override val primaryKey = PrimaryKey(token, name = "token")
    }
}
