package com.example.hashgeneratorapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hashgeneratorapp.model.Hash

@Database(
        entities = [Hash::class],
        version = 1
)
abstract class HashDatabase : RoomDatabase() {

    abstract val hashDao: HashDao

    companion object {
        @Volatile
        private var INSTANCE: HashDatabase? = null

        fun getDatabase(context: Context): HashDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        HashDatabase::class.java,
                        "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}