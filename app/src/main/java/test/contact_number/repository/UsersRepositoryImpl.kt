package test.contact_number.repository

import io.reactivex.Observable
import io.reactivex.Single
import test.contact_number.data_source.CacheDataSource
import test.contact_number.data_source.CloudDataUsers
import test.contact_number.data_source.LocalDataSource
import test.contact_number.model.Users

class UsersRepositoryImpl(
    private val cloudDataUsers: CloudDataUsers,
    private val cacheDataSource: CacheDataSource,
    private val localDataSource: LocalDataSource
    ) : UsersRepository {

    override fun fetchUsers(): Observable<List<Users>> =

        //*?Data not inserting in cache?*//

//        localDataSource.fetchUsers()
//            .flatMapObservable { cachedUsers ->
//                if (cachedUsers.isNotEmpty()) {
//                    Observable.just(cachedUsers)
//                } else {
//                    cloudDataUsers.fetchUsers()
//                        .flatMapObservable {
//                            localDataSource.retain(it)
//                                .toObservable()
//                        }
//                }
//            }

        Observable.concat(
            cacheDataSource
                .fetchUsers()
                .toObservable(),
            localDataSource
                .fetchUsers()
                .toObservable(),
            cloudDataUsers
                .fetchUsers()

                //*?Data not inserting in cache?*//

//                .flatMap(cacheDataSource:: retain)
//                .flatMap(localDataSource:: retain)

                .toObservable()
        )
}