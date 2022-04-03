package test.contact_number.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import test.contact_number.model.Users

@Database(entities = [Users::class], version = 1)
abstract class UsersStorage: RoomDatabase() {

    abstract fun usersDao(): UsersDao
}