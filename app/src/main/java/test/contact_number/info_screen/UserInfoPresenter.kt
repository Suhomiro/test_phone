package test.contact_number.info_screen

import moxy.MvpPresenter

class UserInfoPresenter(): MvpPresenter<UserInfoView>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState?.init()
    }
}