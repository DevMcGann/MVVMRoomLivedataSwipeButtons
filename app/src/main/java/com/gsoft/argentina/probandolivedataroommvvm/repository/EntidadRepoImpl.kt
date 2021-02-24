package com.gsoft.argentina.probandolivedataroommvvm.repository

import androidx.lifecycle.LiveData
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import com.gsoft.argentina.probandolivedataroommvvm.datasource.dataSource
import kotlinx.coroutines.flow.Flow

class EntidadRepoImpl (private val source: dataSource): EntidadRepo{
    override  fun getAllData(): Flow<List<Entidad>?> {
        return source.getAll()
    }

    override fun getLiveData(): LiveData<MutableList<Entidad>> {
        return source.getLiveData()
    }

    override suspend fun saveData(entidad: Entidad) {
        return source.insert(entidad)
    }

    override suspend fun update(entidad: Entidad) {
        return source.update(entidad)
    }

    override suspend fun deleteEntidad(entidad: Entidad) {
        return source.delete(entidad)
    }

    override suspend fun deleteTodo() {
        return source.deleteAll()
    }

}