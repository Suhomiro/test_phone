package test.contact_number.main_screen.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import test.contact_number.databinding.UserItemBinding
import test.contact_number.model.Users

class UserListAdapter(
    var onViewOnClickListener: OnItemClickListener?
) : RecyclerView.Adapter<UserListAdapter.ContactItemViewHolder>() {

    private var usersData = mutableListOf<Users>()
    private var _binding: UserItemBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<Users>) {
        this.usersData = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactItemViewHolder {
        _binding = UserItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ContactItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return usersData.size
    }

    override fun getItemViewType(position: Int): Int = position

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ContactItemViewHolder, position: Int) {
        with(usersData[position]) {
            binding.userEmailTextView.text = this.email
            binding.userNameTextView.text = this.name
            val icon: CharArray = this.name.toCharArray()
            binding.contactIconTextView.text = icon[0].toString()
            binding.contactItemCardView.setOnClickListener {
                onViewOnClickListener?.onItemViewClick(this)
            }
        }
    }

    inner class ContactItemViewHolder(binding: UserItemBinding)  : RecyclerView.ViewHolder(binding.root) {

    }
}