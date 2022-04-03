package test.contact_number.info_screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import test.contact_number.databinding.UserInfoFragmentBinding
import test.contact_number.model.Users

class UserInfoFragment : MvpAppCompatFragment(), UserInfoView {
    companion object {

        const val BUNDLE_EXTRA = "user_info"
        const val REQUEST_CODE = 1

        fun newInstance(bundle: Bundle): UserInfoFragment {
            val fragment = UserInfoFragment()
            fragment.arguments = bundle
            return fragment
        }

    }

    private val presenter: UserInfoPresenter by moxyPresenter { UserInfoPresenter() }
    private var vb: UserInfoFragmentBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        UserInfoFragmentBinding.inflate(inflater, container, false).also {
            vb = it
            presenter
        }.root

    override fun onDestroyView() {
        super.onDestroyView()
        vb = null
    }

    @SuppressLint("SetTextI18n")
    override fun init() {
        val userInfo = arguments?.getParcelable<Users>(BUNDLE_EXTRA)
        userInfo.let {
            val icon: CharArray? = userInfo?.name?.toCharArray()
            vb?.iconInfoTextView?.text = icon?.get(0).toString()
            vb?.nameInfotextView?.text = userInfo?.name
            vb?.numberTextView?.text = userInfo?.phone
            vb?.emailTextView?.text = userInfo?.email
            vb?.streetTextView?.text = userInfo?.address?.street + ","
            vb?.suiteTextView?.text = userInfo?.address?.suite + ","
            vb?.cityTextView?.text = userInfo?.address?.city
        }
        vb?.callFab?.setOnClickListener {
            makePhoneCall()
        }
    }

    private fun makePhoneCall() {
        val number: String = vb?.numberTextView?.text.toString()
        if (number.trim { it <= ' ' }.isNotEmpty()) {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.CALL_PHONE),
                    REQUEST_CODE
                )
            } else {
                val dial = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dial)))
            }
        } else {
            Toast.makeText(context, "Can't call on these number", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall()
                Toast.makeText(context, "You can make call", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission DENIED", Toast.LENGTH_SHORT).show()
            }
        }
    }
}