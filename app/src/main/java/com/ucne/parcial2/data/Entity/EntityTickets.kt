package com.ucne.parcial2.data.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ucne.parcial2.data.remote.dto.TicketDto

@Entity(tableName = "Tickets")
data class EntityTickets(
    val asunto: String,
    val empresa: String,
    val encargadoId: Int,
    val especificaciones: String,
    val estatus: String,
    val fecha: String,
    val orden: Int,
    @PrimaryKey(autoGenerate = true)
    val ticketId: Int ?= null
    )
fun EntityTickets.toTicketDto(): TicketDto {
    return TicketDto(

        asunto = this.asunto,
        empresa = this.empresa,
        encargadoId = this.encargadoId ?:0,
        especificaciones = this.especificaciones,
        estatus = this.estatus,
        fecha = this.fecha,
        orden = this.orden,
        ticketId= this.ticketId ?: 0

    )

}
