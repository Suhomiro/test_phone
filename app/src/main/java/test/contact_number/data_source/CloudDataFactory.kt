package test.contact_number.data_source

import test.contact_number.network.APIFactory

object CloudDataFactory{

    private val cloudDataUsers: CloudDataUsers by lazy {
        CloudDataUsersImpl(
            APIFactory.create()
        )
    }

    fun create(): CloudDataUsers = cloudDataUsers
}