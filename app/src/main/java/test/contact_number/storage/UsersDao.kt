package test.contact_number.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Single
import test.contact_number.model.Users

@Dao
interface UsersDao {
    @Query("SELECT * FROM users")
    fun fetchUsers(): Single<List<Users>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun retain(users : List<Users>): Completable
}