package com.example.appmars.Model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.appmars.Model.Local.MarsDao
import com.example.appmars.Model.Remote.Mars
import com.example.appmars.Model.Remote.MarsRetrofit

class MarsRepository (private val marsDao: MarsDao){

    private val retrofitClient = MarsRetrofit.getRetrofit()

    val dataFromInternet = MutableLiveData<List<Mars>>()

    suspend fun fetchDataFromInternetCoroutines() {
        try {
            val response = retrofitClient.fetchMarsDataCoroutines()
            when (response.code()) {
                //  in 200..299 -> dataFromInternet.value = response.body()
                in 200..299 -> response?.body().let {
                    if (it != null) {
                        // esta insertando en la base de datos Luego que creo la base de datos
                        marsDao.inserTerrains(it)
                    }
                }

                in 300..301 -> Log.d("REPO", "${response.code()} --- ${response.errorBody()}")
                else -> Log.d("REPO", "${response.code()} --- ${response.errorBody().toString()}")
            }
        } catch (t: Throwable) {
            Log.e("REPO", "${t.message}")
        }
    }

    fun  getTerrainByid(id:String) : LiveData<Mars> {
        return getTerrainByid(id)
    }

    // listado de terrenos
    val lisTAllMars : LiveData<List<Mars>> =marsDao.getAllTerrains()


    // insertar un terreno
    suspend fun inserTerrain(mars: Mars) {
        marsDao.insertTerrain(mars)
    }

    // actualizar un terreno

    suspend fun updateTerrain(mars: Mars) {
        marsDao.updateTerrain(mars)
    }

    // elimina terrenos
    suspend fun deleteAll() {
        marsDao.deleteAll()
    }
    //traer terreno por id

    fun getTerrain(id:Int): LiveData<Mars> {
        return  marsDao.getMarsId(id)
    }
}