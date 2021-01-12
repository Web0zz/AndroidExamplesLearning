package com.example.hashgeneratorapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hashgeneratorapp.model.Hash

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHash(hash: Hash)

    @Update
    suspend fun updateHash(hash: Hash)

    @Delete
    suspend fun deleteHash(hash: Hash)

    @Query("SELECT * FROM hash_table")
    fun getAll(): LiveData<List<Hash>>
}