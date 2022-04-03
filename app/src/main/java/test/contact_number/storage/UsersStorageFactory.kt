package test.contact_number.storage

import androidx.room.Room
import test.contact_number.App.ContextHolder.context

object UsersStorageFactory {

    private val inMemoryUsersStorage: UsersStorage by lazy {
        Room.inMemoryDatabaseBuilder(context, UsersStorage::class.java)
            .fallbackToDestructiveMigration()
            .build()
    }

    private val dataBaseUserStorage: UsersStorage by lazy {
        Room.databaseBuilder(context, UsersStorage::class.java, "user.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun create(): UsersStorage = dataBaseUserStorage
}