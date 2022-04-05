package test.contact_number.storage

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single
import test.contact_number.model.Users

@Dao
interface UsersDao {
    @Query("SELECT * FROM users_cache")
    fun fetchUsers(): Single<List<Users>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun retain(users : List<Users>): Completable

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(users: List<Users>): Completable
}