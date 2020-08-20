package dev.jonaz.backend.config

import dev.jonaz.backend.util.atmosphere.NettosphereServer
import dev.jonaz.backend.util.exposed.DatabaseInitializer
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Component
class ApplicationStartup : InitializingBean {
    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun afterPropertiesSet() {
        DatabaseInitializer().connect()

        logger.info("Starting NettoSphere...")
        NettosphereServer().start()
    }
}
