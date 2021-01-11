package com.example.hashgeneratorapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val userName: String,
    val userPassword: String
)