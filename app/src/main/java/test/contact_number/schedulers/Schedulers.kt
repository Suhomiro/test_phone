package test.contact_number.schedulers

import io.reactivex.Scheduler

interface Schedulers {

    fun background(): Scheduler
    fun main(): Scheduler

}