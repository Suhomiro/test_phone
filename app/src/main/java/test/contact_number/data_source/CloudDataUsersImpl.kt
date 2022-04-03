package test.contact_number.data_source

import io.reactivex.Single
import test.contact_number.model.Users
import test.contact_number.network.API

class CloudDataUsersImpl(private val api: API): CloudDataUsers {

    override fun fetchUsers(): Single<List<Users>> =
        api.getUsers()
}