package com.ucne.parcial2.ui.Tickets

import android.app.DatePickerDialog
import android.os.Build
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ucne.parcial2.ui.navigation.ScreenModule
import com.ucne.parcial2.util.opcionesDeEstatus
import kotlinx.coroutines.launch
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TicketsScreen(
    ticketId: Int = 0,
    viewModel: ViewmodelTickets = hiltViewModel(),
    navController: NavController
) {
    remember {
        viewModel.findTicket(ticketId)
        0
    }

    TicketsBody(viewModel, Modifier.fillMaxWidth(), navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TicketsBody(
    viewModel: ViewmodelTickets, modifier: Modifier, navController: NavController
) {

    val scope = rememberCoroutineScope()
    val anio: Int
    val mes: Int
    val dia: Int

    val mCalendar = Calendar.getInstance()
    anio = mCalendar.get(Calendar.YEAR)
    mes = mCalendar.get(Calendar.MONTH)
    dia = mCalendar.get(Calendar.DAY_OF_MONTH)

    val mDatePickerDialog = DatePickerDialog(
        LocalContext.current, { _: DatePicker, anio: Int, mes: Int, dia: Int ->
            //viewModel.fecha = "$dia/${mes + 1}/$anio"
        }, anio, mes, dia
    )

    /*----------------------------------------Code Start------------------------------------------------------*/
    Column(modifier = Modifier.fillMaxWidth())
    {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp, 30.dp)
                .padding(4.dp)
                .clickable {
                    scope.launch {
                        navController.navigateUp()
                    }
                }
        )

        Spacer(modifier = Modifier.padding(20.dp))
        Text(
            text = "Registro de Tickets", fontSize = 27.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(10.dp))

        /*ASUNTO*/
        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.asunto,
            onValueChange = { it -> viewModel.asunto = it },
            label = { Text("Asunto") })

        /*EMPRESA*/
        OutlinedTextField(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            value = viewModel.empresa,
            onValueChange = { it -> viewModel.empresa = it },
            label = { Text("Empresa") })

        /*ESTATUS*/
        var comboBoxExpanded by remember { mutableStateOf(false) }

        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .clickable { comboBoxExpanded = !comboBoxExpanded }
            .fillMaxWidth(),
            enabled = false, readOnly = true,
            value = viewModel.estatus,
            onValueChange = { viewModel.estatus = it },
            label = { Text("Estatus") }
        )
        DropdownMenu(
            expanded = comboBoxExpanded,
            onDismissRequest = { comboBoxExpanded = false },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        ) {
            opcionesDeEstatus.forEach{ estatus ->
                DropdownMenuItem(
                    text = {
                        Text(estatus, textAlign = TextAlign.Center)
                    },
                    onClick={
                        comboBoxExpanded = false
                        viewModel.estatus = estatus
                    },
                    modifier = Modifier.fillMaxWidth().padding(8.dp)
                )
            }
        }

        /*OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.fecha,
            onValueChange = { viewModel.fecha= it },
            enabled = false,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.DateRange,
                    contentDescription = null,
                    modifier = Modifier
                        .size(33.dp)
                        .padding(4.dp)
                        .clickable {
                            mDatePickerDialog.show()
                        })
            },
            label = { Text(text = "Fecha") }
        )*/
        /*ESPECIFICACIONES*/
        OutlinedTextField(modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
            value = viewModel.especificaciones,
            onValueChange = { viewModel.especificaciones = it },
            label = { Text("Especificaciones") }
        )


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(Alignment.BottomCenter)
        ) {
            ExtendedFloatingActionButton(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .wrapContentSize(Alignment.Center),
                text = { Text("Guardar") },
                icon = { Icon(imageVector = Icons.Filled.Save, contentDescription = "Save") },
                onClick = {
                    viewModel.putTicket()
                    navController.navigate(ScreenModule.TicketsList.route)
                }
            )
        }
    }
}