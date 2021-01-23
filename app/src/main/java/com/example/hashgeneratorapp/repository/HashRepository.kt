package com.example.hashgeneratorapp.repository

import androidx.lifecycle.LiveData
import com.example.hashgeneratorapp.data.HashDao
import com.example.hashgeneratorapp.model.Hash

class HashRepository(private val hashDao: HashDao) {

    val readAllHashData: LiveData<List<Hash>> = hashDao.getAll()

    suspend fun getData(id: Int): Hash {
        return hashDao.getfoundata(id)
    }

    suspend fun addHash(hash: Hash) {
        hashDao.insertHash(hash)
    }

    suspend fun deleteHash(hash: Hash) {
        hashDao.deleteHash(hash)
    }
}