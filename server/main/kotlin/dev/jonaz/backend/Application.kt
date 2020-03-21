package dev.jonaz.backend

import dev.jonaz.backend.model.UsersModel
import dev.jonaz.backend.util.exposed.DatabaseInitializer
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationListener
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.sql.ResultSet

@SpringBootApplication
open class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@RestController
class RestController {

    @GetMapping("/")
    fun index(): String {

        transaction {
            addLogger(StdOutSqlLogger)

            SchemaUtils.create(UsersModel)
        }


        return ("{\"test\": true}")
    }

}
