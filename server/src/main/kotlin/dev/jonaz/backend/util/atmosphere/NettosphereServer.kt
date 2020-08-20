package dev.jonaz.backend.util.atmosphere

import org.atmosphere.nettosphere.Config
import org.atmosphere.nettosphere.Nettosphere

class NettosphereServer {

    companion object {
        lateinit var server: Nettosphere

        private val config = Config.Builder()
                .host("0.0.0.0")
                .port(8081)
                .build()
    }


    fun start() {
        server = Nettosphere.Builder().config(config).build()
        server.start()
    }
}
