package dev.jonaz.backend.model.service

data class ServiceRequestModel(
        val session: String,
        val body: Map<*, *>
)
