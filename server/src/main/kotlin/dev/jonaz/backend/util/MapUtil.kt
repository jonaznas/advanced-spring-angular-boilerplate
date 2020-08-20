package dev.jonaz.backend.util

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.jonaz.backend.model.service.ServiceRequestModel

object MapUtil {
    inline fun <reified T> Gson.fromJson(json: String?): T = fromJson(json, object : TypeToken<T>() {}.type)

    inline fun <reified T> json(data: String?): T? = try {
        Gson().fromJson(data)
    } catch (_: Exception) {
        null
    }

    inline fun <reified T> service(data: String): T? = try {
        val request = json<ServiceRequestModel>(data)
        val body = string(request?.body)
        println(request?.session)
        json<T>(body)
    } catch (_: Exception) {
        null
    }

    fun string(data: Any?): String? = try {
        Gson().toJson(data)
    } catch (_: Exception) {
        null
    }
}
