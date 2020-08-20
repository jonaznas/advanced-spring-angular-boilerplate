package dev.jonaz.backend.util.authentication

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.security.Principal

data class UserPrincipal(
        val username: String,
        val authorities: List<SimpleGrantedAuthority>
) : Principal {

    override fun getName(): String {
        return this.username
    }
}
