package dev.jonaz.backend.util.exposed

import org.jetbrains.exposed.sql.transactions.TransactionManager
import java.sql.ResultSet

class DatabaseQuery {
    fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
        val result = arrayListOf<T>()
        TransactionManager.current().exec(this) { rs ->
            while (rs.next()) {
                result += transform(rs)
            }
        }
        return result
    }
}
