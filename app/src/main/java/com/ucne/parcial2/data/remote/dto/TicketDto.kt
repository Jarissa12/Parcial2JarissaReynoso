package com.ucne.parcial2.data.remote.dto

import com.squareup.moshi.Json

data class TicketDto(
    @Json(name = "ticketsId")
    val asunto: String,
    val empresa: String,
    val encargadoId: Int,
    val especificaciones: String,
    val estatus: String,
    var fecha: String,
    val orden: Int,
    val ticketId: Int
)
