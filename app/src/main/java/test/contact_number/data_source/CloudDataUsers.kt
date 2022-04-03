package test.contact_number.data_source

import io.reactivex.Single
import test.contact_number.model.Users

interface CloudDataUsers {
    fun fetchUsers(): Single<List<Users>>
}