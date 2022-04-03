package test.contact_number


import android.os.Bundle
import moxy.MvpAppCompatActivity
import test.contact_number.databinding.ActivityMainBinding
import test.contact_number.main_screen.UserListFragment


class MainActivity : MvpAppCompatActivity() {

    private var vb: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMainBinding.inflate(layoutInflater)
        setContentView(vb?.root)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, UserListFragment.newInstance())
                .commitNow()
        }
    }
}