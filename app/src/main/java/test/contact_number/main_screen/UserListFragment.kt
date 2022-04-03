package test.contact_number.main_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import test.contact_number.R
import test.contact_number.databinding.UserListFragmentBinding
import test.contact_number.info_screen.UserInfoFragment
import test.contact_number.main_screen.adapter.OnItemClickListener
import test.contact_number.main_screen.adapter.UserListAdapter
import test.contact_number.model.Users
import test.contact_number.repository.UsersRepositoryFactory
import test.contact_number.schedulers.DefaultSchedulers

class UserListFragment : MvpAppCompatFragment(), UserListView {

    companion object {
        fun newInstance(): Fragment =
            UserListFragment()
    }

    private val presenter: UserListPresenter by moxyPresenter {
        UserListPresenter(
            UsersRepositoryFactory.create(),
            DefaultSchedulers
        )
    }

    var adapter: UserListAdapter? = null
    private var vb: UserListFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        UserListFragmentBinding.inflate(inflater, container, false).also {
            presenter
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun init(users: List<Users>) {
        vb?.userRecyclerView?.layoutManager = LinearLayoutManager(context)
        adapter = UserListAdapter(
            object : OnItemClickListener {
                override fun onItemViewClick(users: Users) {
                    val manager = activity?.supportFragmentManager
                    if (manager != null) {
                        val bundle = Bundle()
                        bundle.putParcelable(UserInfoFragment.BUNDLE_EXTRA, users)
                        manager.beginTransaction()
                            .add(R.id.container, UserInfoFragment.newInstance(bundle))
                            .addToBackStack("")
                            .commitAllowingStateLoss()
                    }
                }

            }
        )
        adapter!!.setData(users.toMutableList())
        vb?.userRecyclerView?.adapter = adapter
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsers() {
        //adapter?.notifyDataSetChanged()
    }

    override fun showError(error: Throwable) {

    }

}