package com.gsoft.argentina.probandolivedataroommvvm.datasource
import androidx.lifecycle.LiveData
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import kotlinx.coroutines.flow.Flow

class dataSource (private  val dao : EntidadDao){


     fun getAll(): Flow<List<Entidad>?> {
       return dao.getAll()
    }

    fun getLiveData(): LiveData<MutableList<Entidad>>{
        return dao.getLiveData()
    }

    suspend fun update(entidad: Entidad){
        return dao.update(entidad)
    }

    suspend fun insert(entidad: Entidad){
        return dao.insert(entidad)
    }

    suspend fun  delete(entidad: Entidad){
        return dao.delete(entidad)
    }

    suspend fun deleteAll(){
     dao.deleteAll()
    }
}