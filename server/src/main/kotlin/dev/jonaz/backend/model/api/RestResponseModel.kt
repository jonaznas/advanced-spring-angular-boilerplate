package dev.jonaz.backend.model.api

data class RestResponseModel(
        val status: Int,
        val error: Map<*, *> = emptyMap<String, String>()
)
