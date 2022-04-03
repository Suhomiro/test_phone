package test.contact_number.main_screen

import moxy.MvpView
import moxy.viewstate.strategy.AddToEndSingleStrategy
import moxy.viewstate.strategy.StateStrategyType
import moxy.viewstate.strategy.alias.SingleState
import test.contact_number.model.Users

@StateStrategyType(AddToEndSingleStrategy::class)
interface UserListView : MvpView {
    @SingleState
    fun init(users: List<Users>)
    fun updateUsers()
    fun showError(error: Throwable)
}