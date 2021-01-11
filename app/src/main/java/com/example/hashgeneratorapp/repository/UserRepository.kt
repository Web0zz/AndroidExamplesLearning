package com.example.hashgeneratorapp.repository

import androidx.lifecycle.LiveData
import com.example.hashgeneratorapp.data.UserDao
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.model.User
import com.example.hashgeneratorapp.model.UserWithHash

class UserRepository(private val userDao: UserDao) {

    lateinit var readAllHashData: LiveData<List<UserWithHash>>
    val readUserData: LiveData<List<User>> = userDao.getUsers()

    fun getUserHash(user: User){
        readAllHashData = userDao.getUserWithHash(user.userName)
    }

    suspend fun addUser(user: User){
        userDao.insertUser(user)
    }

    suspend fun addHash(hash: Hash){
        userDao.insertHash(hash)
    }

    suspend fun deleteHash(hash: Hash){
        userDao.deleteHash(hash)
    }
}