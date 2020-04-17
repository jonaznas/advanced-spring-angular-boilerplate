package dev.jonaz.backend.util.socket

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SocketMapping(val path: String, val permission: SocketGuard)
