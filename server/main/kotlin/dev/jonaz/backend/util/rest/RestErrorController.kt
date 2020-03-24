package dev.jonaz.backend.util.rest

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class RestErrorController : ErrorController {

    @RequestMapping(value = ["/error"])
    fun error(request: HttpServletRequest, response: HttpServletResponse): Map<*, *>? {
        return mapOf("success" to false, "code" to response.status)
    }

    override fun getErrorPath(): String? {
        return "/error"
    }
}
