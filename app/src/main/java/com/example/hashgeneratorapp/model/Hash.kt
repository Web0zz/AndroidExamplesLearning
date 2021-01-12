package com.example.hashgeneratorapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hash_table")
data class Hash(
    @PrimaryKey(autoGenerate = true)
    val hashId: Int,
    @ColumnInfo(name = "hash_tag") val hashTag: String,
    @ColumnInfo(name = "hash_style") val hashStyle: String,
    @ColumnInfo(name = "hash") val hash: String
)