package com.ucne.parcial2.data.Repository

import com.ucne.parcial2.data.remote.TicketsApi
import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RepositoryTicketsImp @Inject constructor(

    private val api: TicketsApi

) : RepositoryTickets{

    override fun getTickets(): Flow<Resource<List<TicketDto>>> = flow {
            try {
                emit(Resource.Loading()) //indicar que estamos cargando

                val tickets = api.getTickets() //descarga las ocupaciones de internet, se supone quedemora algo

                emit(Resource.Success(tickets)) //indicar que se cargo correctamente y pasarle las monedas
            } catch (e: HttpException) {
                //error general HTTP
                emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
            } catch (e: IOException) {
                //debe verificar tu conexion a internet
                emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
            }
        }

        override suspend fun putTickets(id: Int, ticketDto: TicketDto) {
            api.putTickets(id,ticketDto)
        }

        override suspend fun deleteTickets(id: Int) = api.deleteTickets(id)


    }

