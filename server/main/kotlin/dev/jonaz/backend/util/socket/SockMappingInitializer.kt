package dev.jonaz.backend.util.socket

import com.corundumstudio.socketio.AckRequest
import com.corundumstudio.socketio.SocketIOClient
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.reflections.Reflections
import org.reflections.scanners.MethodAnnotationsScanner
import java.lang.reflect.Method

class SockMappingInitializer {
    private val server = SocketServer.get()

    init {
        val reflections = Reflections("dev.jonaz.backend.controller.sock", MethodAnnotationsScanner())
        val annotated = reflections.getMethodsAnnotatedWith(SockMapping::class.java)

        for (method in annotated) {
            val instance = method.declaringClass.getDeclaredConstructor().newInstance()
            val path = method.getAnnotation(SockMapping::class.java).path

            server.addEventListener(path, Map::class.java)
            { client, fullData, ackRequest -> launchMapping(method, instance, client, fullData, ackRequest) }
        }
    }

    private fun launchMapping(
            method: Method,
            instance: Any,
            client: SocketIOClient,
            fullData: Map<*, *>,
            ackSender: AckRequest) = GlobalScope.launch {
        val sessionToken = fullData["session"].toString()
        val parsedData = fullData["data"] ?: mapOf(null to null)

        val access = method.getAnnotation(SockMapping::class.java).arg1.validateAuthority(sessionToken, client)

        if (access) {
            method.invoke(instance, client, parsedData, ackSender, sessionToken)
        } else {
            ackSender.sendAckData(mapOf("success" to false, "message" to "Access denied"))
        }
    }

}
