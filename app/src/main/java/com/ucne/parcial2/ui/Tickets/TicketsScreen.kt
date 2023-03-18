package com.ucne.parcial2.ui.Tickets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ucne.parcial2.data.remote.dto.TicketDto

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TicketsListScreen(onNewOcupacion: () -> Unit, viewModel: TicketsViewModel = hiltViewModel()) {
    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            TopAppBar(
                title = { Text("Tickets", style = MaterialTheme.typography.headlineLarge) }
            )
        },

        floatingActionButtonPosition = FabPosition.End
    ) {
        val uiState by viewModel.uiState.collectAsState()

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            TicketsListBody(uiState.tickets)
        }
    }
}


@Composable
fun TicketsListBody(ticketsList : List<TicketDto>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        LazyColumn {
            items(ticketsList) {tickets ->
                TicketsRow(tickets)
            }
        }
    }
}

@Composable
fun TicketsRow(tickets: TicketDto) {
    Column(

        Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        //todo : Implementar swipe to delete


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = tickets.empresa,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(3f)
            )
            Text(
                String.format("%.2f", tickets.asunto),
                textAlign = TextAlign.End,
                modifier = Modifier.weight(2f)
            )
        }
        Divider(Modifier.fillMaxWidth())
    }
}
