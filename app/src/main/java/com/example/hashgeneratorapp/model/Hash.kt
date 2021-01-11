package com.example.hashgeneratorapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_hash_table")
data class Hash(
    @PrimaryKey(autoGenerate = false)
    val hashOwnerId: String,
    val hashTag: String,
    val hashStyle: String,
    val hash: String
)