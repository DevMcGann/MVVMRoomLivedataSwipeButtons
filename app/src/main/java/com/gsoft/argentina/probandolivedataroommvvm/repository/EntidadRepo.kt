package com.gsoft.argentina.probandolivedataroommvvm.repository

import androidx.lifecycle.LiveData
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import kotlinx.coroutines.flow.Flow

interface EntidadRepo {
     fun getAllData(): Flow<List<Entidad>?>
    fun getLiveData(): LiveData<MutableList<Entidad>>
    suspend fun saveData(entidad: Entidad)
    suspend fun update(entidad: Entidad)
    suspend fun deleteEntidad(entidad: Entidad)
    suspend fun deleteTodo()
}