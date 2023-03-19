package com.ucne.parcial2.data.remote

import com.squareup.moshi.Json
import com.ucne.parcial2.data.remote.dto.TicketDto
import retrofit2.Response
import retrofit2.http.*


interface TicketsApi{

    @GET("/api/Tickets")
    suspend fun getTickets():List<TicketDto>

    @GET("/api/Tickets/{id}")
    suspend fun getTicketById(@Path("id") id:Int) : TicketDto

    @PUT("/api/tickets/{id}")
    suspend fun putTickets(@Path("id") id: Int, @Body TicketDto : TicketDto ) : Response<Unit>

    @POST("/api/Tickets")
    suspend fun postTickets(@Body ticketDto: TicketDto): TicketDto

    @DELETE("/api/Tickets/{id}")
    suspend fun deleteTickets(@Path("id") id: Int)
}