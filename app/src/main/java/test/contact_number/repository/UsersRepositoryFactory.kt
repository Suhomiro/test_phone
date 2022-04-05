package test.contact_number.repository

import test.contact_number.data_source.CacheDataSourceFactory
import test.contact_number.data_source.CloudDataFactory
import test.contact_number.data_source.LocalDataSourceFactory

object UsersRepositoryFactory {

    private val usersRepository: UsersRepository by lazy {
        UsersRepositoryImpl(
            CloudDataFactory.create(),
            CacheDataSourceFactory.create(),
            LocalDataSourceFactory.create()
        )
    }

    fun create(): UsersRepository = usersRepository
}