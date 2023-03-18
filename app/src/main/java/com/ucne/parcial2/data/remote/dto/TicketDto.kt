package com.ucne.parcial2.data.remote.dto

import com.squareup.moshi.Json

data class TicketDto(
    @Json(name = "ariticuloId")
    val asunto: String,
    val empresa: String,
    val encargadoId: Int,
    val especificaciones: String,
    val estatus: String,
    val fecha: String,
    val orden: Int,
    val ticketId: Int
)
