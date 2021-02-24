package com.gsoft.argentina.probandolivedataroommvvm.presentation

import androidx.lifecycle.*
import com.gsoft.argentina.probandolivedataroommvvm.core.Resource
import com.gsoft.argentina.probandolivedataroommvvm.data.model.Entidad
import com.gsoft.argentina.probandolivedataroommvvm.repository.EntidadRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class EntidadViewModel(private val repo: EntidadRepo) : ViewModel() {

    //var dataList: MutableList<Entidad> = mutableListOf()

    val allStudents : LiveData<MutableList<Entidad>> = repo.getLiveData()


/*    fun fetchEntidades() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())
        try {
            val data = repo.getLiveData()
            dataList.addAll(data as List<Entidad>)
            emit(Resource.Success(dataList))
        } catch (e: Exception) {
            emit(Resource.Failure(e))
        }
    }


    fun fetchAll() = viewModelScope.launch {
        repo.getAllData().distinctUntilChanged()
                .collect { data ->
                    if (data != null) {
                        for (item in data) {
                            val entidad = Entidad(item.id, item.nombre)
                            dataList.add(entidad)
                            print("EtidadList" + dataList.toString())
                        }
                    }
                }
    }*/


    fun saveEntidad(entidad: Entidad) {
        viewModelScope.launch {
            repo.saveData(entidad)
        }
    }

    fun updateEntidad (entidad: Entidad){
        viewModelScope.launch {
            repo.update(entidad)
        }
    }


    fun wipeDB(){
        viewModelScope.launch {
            repo.deleteTodo()
        }
    }

    fun deleteEntidad(entidad:Entidad){
        viewModelScope.launch {
            repo.deleteEntidad(entidad)
        }
    }


}




class EntidadViewModelFactory(private val repo: EntidadRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(EntidadRepo::class.java).newInstance(repo)
    }
}