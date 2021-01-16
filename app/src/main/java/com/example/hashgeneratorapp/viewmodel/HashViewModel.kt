package com.example.hashgeneratorapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hashgeneratorapp.data.HashDatabase
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.repository.HashRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest

class HashViewModel(application: Application): AndroidViewModel(application) {

    private val repository: HashRepository
    val hashDatabaseList: LiveData<List<Hash>>

    init{
        val userDao = HashDatabase.getDatabase(application).hashDao
        repository = HashRepository(userDao)
        hashDatabaseList = repository.readAllHashData
    }

    fun addNewHash(hash: Hash) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHash(hash)
        }
    }

    fun updateHash(hash: Hash) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateHash(hash)
        }
    }

    fun deleteHash(hash: Hash) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHash(hash)
        }
    }

    fun getHashText(text: String, algorithm: String): String {
        val bytes = MessageDigest.getInstance(algorithm).digest(text.toByteArray())
        return toHexType(bytes)
    }

    private fun toHexType(byteArray: ByteArray): String{
        return byteArray.joinToString("") { "%02x".format(it) }
    }

}