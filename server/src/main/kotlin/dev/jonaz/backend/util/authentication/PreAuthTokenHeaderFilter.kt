package dev.jonaz.backend.util.authentication

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class PreAuthTokenHeaderFilter(private var authHeaderName: String) : AbstractPreAuthenticatedProcessingFilter() {

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest): String {
        return request.getHeader(authHeaderName) ?: ""
    }

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest) {

    }
}
