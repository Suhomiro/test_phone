package test.contact_number.repository

import test.contact_number.data_source.CacheDataSourceFactory
import test.contact_number.data_source.CloudDataFactory

object UsersRepositoryFactory {

    fun create(): UsersRepository =
            UsersRepositoryImpl(
                CloudDataFactory.create(),
                CacheDataSourceFactory.create()
            )
}