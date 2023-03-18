package com.ucne.parcial2.ui.Tickets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucne.parcial2.data.Entity.EntityTickets
import com.ucne.parcial2.data.Repository.RepositoryTickets
import com.ucne.parcial2.data.Repository.RepositoryTickets2
import com.ucne.parcial2.data.remote.dto.TicketDto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class TicketsUiState(

    val TicketsList: List<EntityTickets> = emptyList()

)

@HiltViewModel
class ViewmodelTickets @Inject constructor(

    private val repositoryTickets: RepositoryTickets2

) : ViewModel(){

    var asunto by mutableStateOf("")
    var empresa by mutableStateOf("")
    var encargadoId by mutableStateOf("")
    var especificaciones by mutableStateOf("")
    var estatus by mutableStateOf("")
    var fecha by mutableStateOf("")
    var orden by mutableStateOf("")

    var isValid: Boolean by mutableStateOf(false)

    var uiState = MutableStateFlow(TicketsUiState())
        private set

    init {
        getLista()
    }

    fun getLista() {
        viewModelScope.launch(Dispatchers.IO) {
            refrescarTicket()
        }
    }

//    private  suspend fun getTicketsFromApi(){
//        val ticket = getTicketsFromApi().
//        uiState.update {
//            it.copy(ticketsList = ticket)
//        }
//    }

    private suspend fun refrescarTicket() {
        repositoryTickets.getList().collect { lista ->
            uiState.update {
                it.copy(TicketsList = lista)
            }
        }
    }

    fun insertar() {

        val ticket = EntityTickets(
            asunto = asunto,
            empresa = empresa,
            encargadoId = encargadoId.toIntOrNull() ?: 0,
            especificaciones = especificaciones,
            estatus = estatus,
            fecha = fecha,
            orden = orden.toIntOrNull() ?: 0
        )

        viewModelScope.launch(Dispatchers.IO) {
            repositoryTickets.insert(ticket)
            Limpiar()
        }
    }

    private fun Limpiar() {
        asunto = ""
        empresa = ""
        encargadoId = ""
        especificaciones = ""
        estatus = ""
        fecha = ""
        orden = ""

    }
}

