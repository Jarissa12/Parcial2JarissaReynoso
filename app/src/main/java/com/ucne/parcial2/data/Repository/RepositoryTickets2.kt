package com.ucne.parcial2.data.Repository

import com.ucne.parcial2.data.Entity.EntityTickets
import com.ucne.parcial2.data.Entity.toTicketDto
import com.ucne.parcial2.data.dao.TicketsDao
import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryTickets2  @Inject constructor(
    private  val ticketsDao: TicketsDao,
    private  val ticketsApi: TicketsApi
) {

    suspend fun insert(tickets: EntityTickets) {

        ticketsDao.insert(tickets) //insertar en la base de datos

        //ticketsApi.putTickets(tickets.ticketId!! ,tickets.toTicketDto())


    }
    suspend fun delete(tickets: EntityTickets) = ticketsDao.delete(tickets)

    suspend fun find(ticketId:Int) = ticketsDao.find(ticketId)
    suspend fun putTickets(id: Int, ticketDto: TicketDto) = ticketsApi.putTickets(id,ticketDto)

    fun getList(): Flow<List<EntityTickets>> = ticketsDao.getList()

}