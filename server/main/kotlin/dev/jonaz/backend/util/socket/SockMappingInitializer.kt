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
            val instance = method.declaringClass.newInstance()
            val path = method.getAnnotation(SockMapping::class.java).path

            println(path)

            server.addEventListener(path, Map::class.java) { client, data, ackRequest -> launchMapping(method, instance, client, data, ackRequest) }
        }
    }

    private fun launchMapping(
            method: Method,
            instance: Any,
            client: SocketIOClient,
            data: Map<*, *>,
            ackSender: AckRequest) = GlobalScope.launch {
        method.invoke(instance, client, data, ackSender)
    }

}
