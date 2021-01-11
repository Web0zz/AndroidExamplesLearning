package com.example.hashgeneratorapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.model.User

@Database(
        entities = [
            User::class,
            Hash::class],
        version = 1
)
abstract class UserDatabase : RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        UserDatabase::class.java,
                        "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}