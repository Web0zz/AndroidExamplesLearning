package com.example.hashgeneratorapp.repository

import androidx.lifecycle.LiveData
import com.example.hashgeneratorapp.data.UserDao
import com.example.hashgeneratorapp.model.Hash

class HashRepository(private val userDao: UserDao) {

    val readAllHashData: LiveData<List<Hash>> = userDao.getAll()

    suspend fun addHash(hash: Hash){
        userDao.insertHash(hash)
    }

    suspend fun updateHash(hash: Hash){
        userDao.updateHash(hash)
    }

    suspend fun deleteHash(hash: Hash){
        userDao.deleteHash(hash)
    }
}