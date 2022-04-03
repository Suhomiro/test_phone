package test.contact_number.data_source

import test.contact_number.network.APIFactory

object CloudDataFactory{

    fun create(): CloudDataUsers = CloudDataUsersImpl(APIFactory.create())
}