package com.example.appmars.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.appmars.Model.Local.MarsDataBase
import com.example.appmars.Model.MarsRepository
import com.example.appmars.Model.Remote.Mars
import kotlinx.coroutines.launch

class MarsViewModel(application: Application): AndroidViewModel(application)  {

    private val repository: MarsRepository

    private lateinit var liveDatafromInternet: LiveData<List<Mars>>


    val allTerrains: LiveData<List<Mars>>


    init {

        //parte 1 funciona sin el dao
        // repository= MarsRepository()
        // liveDatafromInternet = repository.fetchDataMars()


        // parte 2
        val MarsDao = MarsDataBase.getDataBase(application).getMarsDao()
        repository = MarsRepository(MarsDao)

        // parte 2 con corrutinas y viewmodel

        viewModelScope.launch {

            repository.fetchDataFromInternetCoroutines()
        }
        // cierre parte 2

        allTerrains = repository.lisTAllMars

        /**********parte 1***********************/
        liveDatafromInternet = repository.dataFromInternet

    }
    private var selectedMarsTerrains: MutableLiveData<Mars> = MutableLiveData()

    fun selected(mars: Mars) {
        selectedMarsTerrains.value = mars
    }

    fun selectedItem(): LiveData<Mars> = selectedMarsTerrains


    fun inserTerrain(mars: Mars) = viewModelScope.launch {

        repository.inserTerrain(mars)
    }


    fun getMarsList(): LiveData<List<Mars>> =repository.lisTAllMars

    fun updateTerrain(mars: Mars) = viewModelScope.launch {
        repository.updateTerrain(mars)
    }

    fun getMarsById(id:Int): LiveData<Mars>{
        return  repository.getTerrain(id)
    }
}