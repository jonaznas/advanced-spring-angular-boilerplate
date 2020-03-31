package dev.jonaz.backend.util.socket

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import dev.jonaz.backend.util.session.SessionManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reflections.Reflections
import org.reflections.scanners.MethodAnnotationsScanner
import java.lang.reflect.Method

class SocketMappingInitializer {
    private val server = SocketServer.get()

    init {
        val reflections = Reflections("dev.jonaz.backend.controller.sock", MethodAnnotationsScanner())
        val annotated = reflections.getMethodsAnnotatedWith(SocketMapping::class.java)

        for (method in annotated) {
            val instance = method.declaringClass.getDeclaredConstructor().newInstance()
            val path = method.getAnnotation(SocketMapping::class.java).path

            server.addEventListener(path, Map::class.java)
            { client, fullData, ackRequest -> launchMapping(method, instance, client, fullData, ackRequest) }
        }
    }

    private fun launchMapping(
            method: Method,
            instance: Any,
            client: SocketIOClient,
            fullData: Map<*, *>?,
            ackSender: AckRequest) = GlobalScope.launch {

        val sessionToken = fullData?.getOrDefault("session", null).toString()
        val session = SessionManager.get(sessionToken)
        val parsedData = fullData?.getOrDefault("data", emptyMap<Any, Any>())

        val access = method.getAnnotation(SocketMapping::class.java).permission.validateAuthority(sessionToken, client)

        if (access) {
            method.invoke(instance, client, parsedData, ackSender, session)
        } else {
            ackSender.sendAckData(mapOf("success" to false, "message" to "Access denied"))
        }
    }
}
