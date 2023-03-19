package com.ucne.parcial2.ui.Tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import com.ucne.parcial2.data.Repository.RepositoryTickets

import com.ucne.parcial2.data.remote.dto.TicketDto
import com.ucne.parcial2.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TicketsListUiState(
    val estaCargando: Boolean = false, //Is Loading
    val tickets: List<TicketDto> = emptyList(), //Success
    val mensajeError: String? = null //Error
)

data class TicketUiState(
    val estaCargando: Boolean = false,
    val ticket : TicketDto? = null,
    val mensajeError: String? = null
)

@HiltViewModel
class ViewmodelTickets @Inject constructor(
    private val ticketRepos: RepositoryTickets
) : ViewModel() {

    var ticketId by mutableStateOf(0)

    var asunto by mutableStateOf("")
    var empresa by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")


    /* ESTADO DE LA LISTA */
    var listUiState = MutableStateFlow(TicketsListUiState())
        private set

    /* ESTADO DE UN SOLO TICKET */
    var uiState = MutableStateFlow(TicketUiState())
        private set

    init {
        ticketRepos.getTickets().onEach { resultado -> //resultado de la llamada a la API
            when (resultado) {
                is Resource.Loading -> {
                    listUiState.update { ticketList ->
                        ticketList.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    listUiState.update{ ticketList ->
                        ticketList.copy(tickets =  resultado.data ?: emptyList(), estaCargando = false)
                    }
                }
                is Resource.Error -> {
                    listUiState.update { ticketList ->
                        ticketList.copy(mensajeError = resultado.message)
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    /*fun insertar() {

        val ticket = EntityTickets(
            asunto = asunto,
            empresa = empresa,
            //encargadoId = encargadoId.toIntOrNull() ?: 0,
            especificaciones = especificaciones,
            estatus = estatus
            //fecha = fecha,
            //orden = orden.toIntOrNull() ?: 0
        )

        viewModelScope.launch(Dispatchers.IO) {
        }
    }*/

    fun findTicket(id: Int) {
        ticketId = id
        limpiar()
        ticketRepos.getTicketById(id).onEach {resultado -> //resultado de la llamada a la API
            when(resultado){
                is Resource.Loading -> {
                    uiState.update {estado ->
                        estado.copy(estaCargando = true)
                    }
                }
                is Resource.Success -> {
                    uiState.update {estado ->
                        /*ALMACENAR LO CAMPOS DE LA API TEMPORALMENTE*/
                        estado.copy(ticket = resultado.data, estaCargando = false)
                    }
                    /*ACTUALIZAR LOS CAMPOS QUE SE VISUALIZAN EN LA UI*/
                    asunto = uiState.value.ticket!!.asunto
                    empresa = uiState.value.ticket!!.empresa
                    especificaciones = uiState.value.ticket!!.especificaciones
                    estatus = uiState.value.ticket!!.estatus
                }
                is Resource.Error -> {
                    uiState.update {estado ->
                        estado.copy(mensajeError = resultado.message ?: "ERROR")
                    }
                }
            }
        }.launchIn(viewModelScope)

    }
    fun putTicket(){
        viewModelScope.launch {
            ticketRepos.putTickets(ticketId,
                TicketDto(
                    asunto = asunto,
                    empresa = empresa,
                    encargadoId = uiState.value.ticket!!.encargadoId,
                    especificaciones = especificaciones,
                    estatus = estatus,
                    fecha = uiState.value.ticket!!.fecha,
                    orden = uiState.value.ticket!!.orden,
                    ticketId = ticketId
                )
            )
        }
    }
    private fun limpiar() {
        asunto = ""
        empresa = ""
        especificaciones = ""
        estatus = ""
    }
}


