package dev.jonaz.backend.util.authentication

import dev.jonaz.backend.component.auth.SessionManager
import dev.jonaz.backend.model.user.SessionModel
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class AuthenticationFilter : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val servletRequest = request as HttpServletRequest
        val token = servletRequest.getHeader("Authorization") ?: ""

        val session = SessionManager.validate(token)

        when {
            session != null -> authorize(session)
        }

        chain.doFilter(request, response)
    }

    private fun authorize(session: SessionModel) {
        val role = session.role
        val user = session.user

        val authority = SimpleGrantedAuthority(role)
        val principal = UserPrincipal(user.toString(), listOf(authority))

        val authentication = UsernamePasswordAuthenticationToken(principal, null, principal.authorities)
        SecurityContextHolder.getContext().authentication = authentication
    }
}
