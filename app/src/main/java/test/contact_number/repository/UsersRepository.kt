package test.contact_number.repository

import io.reactivex.Observable
import test.contact_number.model.Users

interface UsersRepository {
    fun fetchUsers(): Observable<List<Users>>
}