package test.contact_number.data_source

import test.contact_number.storage.UsersStorageFactory

object LocalDataSourceFactory{

    private val localDataSource: LocalDataSource by lazy {
        LocalDataSourceImpl(
            UsersStorageFactory.createDB()
        )
    }

    fun create(): LocalDataSource = localDataSource
}