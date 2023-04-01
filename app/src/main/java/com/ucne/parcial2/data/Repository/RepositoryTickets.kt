package com.ucne.parcial2.data.Repository

import androidx.room.Dao
import com.ucne.parcial2.data.Entity.EntityTickets
import com.ucne.parcial2.data.Entity.toTicketDto
import com.ucne.parcial2.data.dao.TicketsDao
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow


interface RepositoryTickets{




    fun getTickets(): Flow<Resource<List<TicketDto>>>
    fun getTicketById(id: Int): Flow<Resource<TicketDto>>
    suspend fun putTickets(id: Int, ticketDto: TicketDto)
    suspend fun deleteTickets(id: Int)




}