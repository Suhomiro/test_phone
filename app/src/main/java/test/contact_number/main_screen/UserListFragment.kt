package test.contact_number.main_screen

import android.annotation.SuppressLint
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import test.contact_number.R
import test.contact_number.databinding.UserListFragmentBinding
import test.contact_number.info_screen.UserInfoFragment
import test.contact_number.main_screen.adapter.OnItemClickListener
import test.contact_number.main_screen.adapter.UserListAdapter
import test.contact_number.model.Users
import test.contact_number.network_state.ConnectivityReceiver
import test.contact_number.repository.UsersRepositoryFactory
import test.contact_number.schedulers.DefaultSchedulers
import test.contact_number.utils.fadeInAnimation
import test.contact_number.utils.fadeOutAnimation

class UserListFragment : MvpAppCompatFragment(), UserListView, ConnectivityReceiver.ConnectivityReceiverListener {

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

    private var adapter: UserListAdapter? = null
    private var vb: UserListFragmentBinding? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        UserListFragmentBinding.inflate(inflater, container, false).also {
            requireActivity().registerReceiver(ConnectivityReceiver(), IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
            presenter
            vb = it
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    override fun onResume() {
        super.onResume()
        ConnectivityReceiver.connectivityReceiverListener = this
    }

    override fun init(users: List<Users>) {
        dataInit(users)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun updateUsers(users: List<Users>) {
        val refreshListener = SwipeRefreshLayout.OnRefreshListener {
            vb!!.swipeRefreshLayout.isRefreshing = true
            dataInit(users)
        }
        vb!!.swipeRefreshLayout.setOnRefreshListener(refreshListener)
    }

    override fun showError(error: Throwable) {

    }

    override fun onNetworkConnectionChanged(isConnected: Boolean) {
        showNetworkMessage(isConnected)
    }

    private fun dataInit(users: List<Users>){
        vb!!.swipeRefreshLayout.isRefreshing = false
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

        if(!users.isNullOrEmpty()) {
            adapter!!.setData(users.toMutableList())
        } else {
            loader()
        }
        vb?.userRecyclerView?.adapter = adapter
    }

    private fun loader(){

        Handler().postDelayed({
            vb!!.loaderFrameLayout.visibility = View.VISIBLE
        }, 0)
        Handler().postDelayed({
            fadeOutAnimation(vb!!.loaderFrameLayout)
            fadeOutAnimation(vb!!.animationViewSplash)
        }, 3500)
    }

    private fun showNetworkMessage(isConnected: Boolean) {
        if (isConnected) {
            Toast.makeText(context, "Connected", Toast.LENGTH_SHORT).show()
        } else {
            loader()
            fadeInAnimation(vb!!.phoneBookTextView)
            Toast.makeText(context, "Disconnected", Toast.LENGTH_SHORT).show()
            }
        }

}