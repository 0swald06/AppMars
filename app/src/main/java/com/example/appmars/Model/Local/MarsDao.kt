package com.example.appmars.Model.Local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.appmars.Model.Remote.Mars


@Dao
interface MarsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerrain(mars: Mars)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserTerrains(mars: List<Mars>)

    @Update
    suspend fun updateTerrain(mars: Mars)

    @Delete
    suspend fun  deleteTerrain(mars: Mars)


    @Query("DELETE FROM mars_table")
    suspend fun deleteAll()


    // traer todos los terrenos

    @Query("SELECT * FROM mars_table ORDER BY id DESC")
    fun getAllTerrains(): LiveData<List<Mars>>


    @Query("SELECT * FROM mars_table WHERE type=:type Limit 1")
    fun getMarsType( type: String): LiveData<Mars>


    @Query("SELECT * FROM mars_table WHERE id=:id")
    fun getMarsId(id:Int): LiveData<Mars>



}