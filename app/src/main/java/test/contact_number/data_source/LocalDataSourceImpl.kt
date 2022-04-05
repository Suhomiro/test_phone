package test.contact_number.data_source

import io.reactivex.Single
import test.contact_number.model.Users
import test.contact_number.storage.UsersStorage

class LocalDataSourceImpl(private val usersStorage: UsersStorage): LocalDataSource {

    override fun fetchUsers(): Single<List<Users>> =
        usersStorage
            .usersDao()
            .fetchUsers()

    override fun retain(users: List<Users>): Single<List<Users>> =
        usersStorage
            .usersDao()
            .retain(users)
            .andThen(fetchUsers())

    override fun update(users: List<Users>): Single<List<Users>> =
        usersStorage
            .usersDao()
            .update(users)
            .andThen(fetchUsers())


}