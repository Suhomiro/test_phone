package test.contact_number.data_source

import test.contact_number.storage.UsersStorageFactory

object CacheDataSourceFactory{

    private val cacheDataSource: CacheDataSource by lazy {
        CacheDataSourceImpl(UsersStorageFactory.create())
    }

    fun create(): CacheDataSource = cacheDataSource
}