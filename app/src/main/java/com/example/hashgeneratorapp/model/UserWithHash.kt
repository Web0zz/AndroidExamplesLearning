package com.example.hashgeneratorapp.model

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithHash(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userName",
        entityColumn = "hashOwnerId"
    )
    val hashList: List<Hash>
)