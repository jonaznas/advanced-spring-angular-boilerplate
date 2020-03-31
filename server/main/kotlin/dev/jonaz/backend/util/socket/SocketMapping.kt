package dev.jonaz.backend.util.socket

import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
annotation class SocketMapping(val path: String, val permission: SocketGuard)
