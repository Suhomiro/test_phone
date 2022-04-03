package test.contact_number.repository

import io.reactivex.Observable
import io.reactivex.Single
import test.contact_number.model.Users

interface UsersRepository {
    fun fetchUsers(): Observable<List<Users>>
}