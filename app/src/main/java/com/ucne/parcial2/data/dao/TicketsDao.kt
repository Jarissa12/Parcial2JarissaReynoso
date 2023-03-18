package com.ucne.parcial2.data.dao

import androidx.room.*
import com.ucne.parcial2.data.Entity.EntityTickets
import kotlinx.coroutines.flow.Flow

@Dao
interface TicketsDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)

    suspend fun insert (entityTickets: EntityTickets)

    @Delete
    suspend fun delete(entityTickets: EntityTickets)

    @Query("""
        SELECT * 
        FROM Tickets
        WHERE TicketId=:TicketId
        LIMIT 1
    """)
    suspend fun find(TicketId: Int): EntityTickets?

    @Query("""SELECT * 
        FROM Tickets
        ORDER BY TicketId desc
    """)

    fun getList(): Flow<List<EntityTickets>>


}

class dao{

    fun save():Boolean{
        return true
    }

}

