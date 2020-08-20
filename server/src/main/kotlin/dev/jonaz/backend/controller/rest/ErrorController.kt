package dev.jonaz.backend.controller.rest

import dev.jonaz.backend.model.api.RestResponseModel
import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
class ErrorController : ErrorController {

    @RequestMapping(value = ["/error"])
    fun error(request: HttpServletRequest, response: HttpServletResponse): RestResponseModel {
        return RestResponseModel(status = response.status)
    }

    override fun getErrorPath(): String? {
        return "/error"
    }
}
