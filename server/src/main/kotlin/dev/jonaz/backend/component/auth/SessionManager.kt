package dev.jonaz.backend.component.auth

import dev.jonaz.backend.model.DatabaseTable
import dev.jonaz.backend.model.user.SessionModel
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime
import java.util.*
import kotlin.streams.asSequence

class SessionManager {
    companion object {
        private val table = DatabaseTable.Session

        fun get(token: String) = transaction {
            table.select { table.token eq token }.map {
                SessionModel(it[table.token], it[table.user], it[table.meta], it[table.role], it[table.created], it[table.expire])
            }
        }

        fun validate(session: SessionModel): SessionModel? {
            val list = this.get(session.token)

            return when {
                list.size.equals(1).not() -> null
                else                      -> list.get(0)
            }
        }

        fun validate(list: List<SessionModel>) = when {
            list.size.equals(1).not() -> null
            else                      -> validate(list.get(0))
        }

        fun validate(token: String) = this.validate(
                this.get(token)
        )

        fun create(user: Int, role: String): String {
            val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.:,-#"
            val token = Random().ints(128, 0, source.length).asSequence().map(source::get).joinToString("")

            transaction {
                table.deleteWhere { table.user eq user }
                table.insert {
                    it[table.token] = token
                    it[table.user] = user
                    it[table.meta] = "{}"
                    it[table.role] = role
                    it[table.created] = LocalDateTime.now()
                    it[table.expire] = LocalDateTime.now().plusDays(14)
                }
            }

            return token
        }
    }
}
