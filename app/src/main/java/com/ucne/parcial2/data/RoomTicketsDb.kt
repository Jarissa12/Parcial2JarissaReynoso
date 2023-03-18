package com.ucne.parcial2.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ucne.parcial2.data.Entity.EntityTickets
import com.ucne.parcial2.data.dao.TicketsDao


@Database(
    entities = [
        EntityTickets::class
    ],
    version = 1
)
abstract class RoomTicketsDb : RoomDatabase() {
    abstract val ticketDao: TicketsDao
}