package com.ucne.parcial2.ui.Tickets

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.lifecycle.ViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ticketsScreenRegistro(

    viewModel:ViewmodelTickets = hiltViewModel()
) {
     Column( modifier = Modifier.fillMaxWidth()) {
         Spacer(modifier = Modifier
             .fillMaxSize()
             .padding(8.dp))
         Text(text = "Registro de Tickets", color = Color.Black, textAlign = TextAlign.Center)

          Spacer(modifier = Modifier .padding(8.dp))


         OutlinedTextField(
             modifier = Modifier
                 .padding(8.dp)
                 .fillMaxWidth(),
             value = viewModel.asunto,
             onValueChange = { viewModel.asunto= it },
             label = { Text("Asunto ") }
         )

         OutlinedTextField(
             modifier = Modifier
                 .padding(8.dp)
                 .fillMaxWidth(),
             value = viewModel.empresa,
             onValueChange = { viewModel.empresa = it },
             label = { Text("Empresa") }
         )

     }

 }

