package dev.jonaz.backend.controller.service

import dev.jonaz.backend.util.MapUtil
import org.atmosphere.config.service.Disconnect
import org.atmosphere.config.service.ManagedService
import org.atmosphere.config.service.Message
import org.atmosphere.config.service.Ready
import org.atmosphere.cpr.AtmosphereResource
import org.atmosphere.cpr.AtmosphereResourceEvent

@ManagedService(path = "/echo")
class EchoService {

    @Ready
    fun onReady(resource: AtmosphereResource) {
        println("triggered onReady")
    }

    @Disconnect
    fun onDisconnect(event: AtmosphereResourceEvent) {
        println("triggered onDisconnect")
    }

    @Message
    fun onMessage(resource: AtmosphereResource, raw: String) {
        val test = MapUtil.service<Request>(raw) ?: return

        println("triggered onMessage")

        resource.write("test response")
    }

    data class Request(
            val message: String
    )
}
