package test.contact_number.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

object DefaultSchedulers : Schedulers {

    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun main(): Scheduler = AndroidSchedulers.mainThread()
}