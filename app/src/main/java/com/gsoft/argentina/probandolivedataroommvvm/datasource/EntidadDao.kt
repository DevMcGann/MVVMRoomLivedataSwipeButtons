package com.gsoft.argentina.probandolivedataroommvvm.datasource

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import kotlinx.coroutines.flow.Flow

@Dao
interface EntidadDao {

    @Query("SELECT * FROM Entidad")
     fun getAll(): Flow<List<Entidad>?>

    @Query("SELECT * FROM Entidad")
    fun getLiveData(): LiveData<MutableList<Entidad>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entidad: Entidad)

    @Update
    suspend fun update(entidad: Entidad)

    @Delete
    suspend fun  delete(entidad: Entidad)

    @Query("DELETE  FROM Entidad")
    suspend fun deleteAll()

}