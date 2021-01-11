package com.example.hashgeneratorapp.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.model.User
import com.example.hashgeneratorapp.model.UserWithHash

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHash(hash: Hash)

    //Get users hash data by username
    @Transaction
    @Query("SELECT * FROM user_table WHERE userName = :userName")
    fun getUserWithHash(userName: String): LiveData<List<UserWithHash>>

    //Get users list for authentication the user
    @Query("SELECT * FROM user_table")
    fun getUsers(): LiveData<List<User>>

    @Delete
    suspend fun deleteHash(hash: Hash)
}