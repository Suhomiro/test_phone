package test.contact_number.data_source

import io.reactivex.Single
import test.contact_number.model.Users

interface CacheDataSource {

    fun fetchUsers(): Single<List<Users>>
    fun retain(users: List<Users>): Single<List<Users>>
}