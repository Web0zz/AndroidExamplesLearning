package com.example.hashgeneratorapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hashgeneratorapp.data.UserDatabase
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.model.User
import com.example.hashgeneratorapp.model.UserWithHash
import com.example.hashgeneratorapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {

    lateinit var readUserHashData: LiveData<List<UserWithHash>>
    val readUserdata: LiveData<List<User>>
    private val repository: UserRepository

    init{
        val userDao = UserDatabase.getDatabase(application).userDao
        repository = UserRepository(userDao)
        readUserdata = repository.readUserData
    }

    fun getUserHashData(user: User){
        repository.getUserHash(user)
        readUserHashData = repository.readAllHashData
    }

    fun addNewUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun addNewHash(hash: Hash){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHash(hash)
        }
    }

    fun deleteHash(hash: Hash){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteHash(hash)
        }
    }

}