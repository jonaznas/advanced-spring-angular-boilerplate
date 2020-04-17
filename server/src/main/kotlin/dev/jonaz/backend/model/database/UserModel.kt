package dev.jonaz.backend.model.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.`java-time`.datetime
import java.time.LocalDateTime

object UserModel : Table("users") {
    val id = integer("id").autoIncrement()
    val username = text("username")
    val password = text("password")
    val createdAt = datetime("createdAt").default(LocalDateTime.now())

    override val primaryKey = PrimaryKey(id, name = "id")
}
