package test.contact_number.data_source

import io.reactivex.Single
import test.contact_number.model.Users
import test.contact_number.storage.UsersStorage

class CacheDataSourceImpl(private val usersStorage: UsersStorage): CacheDataSource {

    override fun fetchUsers(): Single<List<Users>> =
        usersStorage
            .usersDao()
            .fetchUsers()

    override fun retain(users: List<Users>): Single<List<Users>> =
        usersStorage
            .usersDao()
            .retain(users)
            .andThen(fetchUsers())

}