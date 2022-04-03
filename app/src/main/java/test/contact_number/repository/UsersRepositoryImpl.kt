package test.contact_number.repository

import io.reactivex.Observable
import test.contact_number.data_source.CacheDataSource
import test.contact_number.data_source.CloudDataUsers
import test.contact_number.model.Users

class UsersRepositoryImpl(
    private val cloudDataUsers: CloudDataUsers,
    private val cacheDataSource: CacheDataSource
    ) : UsersRepository {

    override fun fetchUsers(): Observable<List<Users>> =

        Observable.concat(
            cacheDataSource
                .fetchUsers()
                .toObservable(),
            cloudDataUsers
                .fetchUsers()
                .flatMap(cacheDataSource::retain)
                .toObservable()
        )
}