package test.contact_number.main_screen

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import moxy.MvpPresenter
import test.contact_number.model.Users
import test.contact_number.repository.UsersRepository
import test.contact_number.schedulers.Schedulers

class UserListPresenter(
    private val userRepository: UsersRepository,
    private val schedulers: Schedulers
): MvpPresenter<UserListView>() {

    private var disposables =  CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadData()

    }

    private fun loadData(){
        disposables +=
            userRepository
                .fetchUsers()
                .map { it.map(UserMapper::map) }
                .observeOn(schedulers.main())
                .subscribeOn(schedulers.background())
                .subscribe(
                    :: fetchDataSuccess,
                    :: fetchDataOnError
                )
    }

    private fun fetchDataSuccess(users: List<Users>) {
        viewState.init(users)
    }

    private fun fetchDataOnError(error: Throwable){
        viewState.showError(error)
    }

    override fun onDestroy() {
        super.onDestroy()
        disposables.dispose()
    }
}