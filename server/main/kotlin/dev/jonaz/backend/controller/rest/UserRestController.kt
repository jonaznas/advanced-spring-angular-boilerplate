package dev.jonaz.backend.controller.rest

import dev.jonaz.backend.util.socket.SockMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.reflect.Method
import kotlin.reflect.jvm.kotlinFunction

@RestController
class UserRestController {

    @GetMapping("/")
    fun index(): String {

        /*transaction {
            addLogger(StdOutSqlLogger)
        }*/

        bindReflectionValue(this)

        return ("{\"test\": true}")
    }

    @SockMapping("/test2")
    fun index2() {
        println("test")
    }

    private fun bindReflectionValue(target: Any) {



        val declaredFields = target::class.java.declaredMethods

        for (field in declaredFields) {
            for (annotation in field.annotations) {
                when (annotation) {
                    is SockMapping -> {
                        field.invoke()
                    }
                }
            }
        }
    }

}

private fun Method.invoke() {
    println("abc")
}
