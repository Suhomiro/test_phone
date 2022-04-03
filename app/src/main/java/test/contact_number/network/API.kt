package test.contact_number.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import test.contact_number.model.Users

interface API {
    @GET("/users")
    fun getUsers(@Query("id") id: Int? = null): Single<List<Users>>
}