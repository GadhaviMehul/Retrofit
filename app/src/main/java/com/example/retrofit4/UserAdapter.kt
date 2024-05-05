package com.example.retrofit4

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit4.databinding.SingleRowBinding

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    private var userData = emptyList<UserData.Data>()
    var onItemClick: ((UserData.Data) -> Unit)? = null

    inner class UserViewHolder(private val itemList: SingleRowBinding) :
        RecyclerView.ViewHolder(itemList.root) {
        fun bindUserData(data: UserData.Data) {
            itemList.firstName.text = data.firstName
            itemList.lastName.text = data.lastName
            itemList.title.text = data.title+".".toString()
            itemList.userId.text = data.id
            Glide.with(itemList.imageView.context).load(data.picture).into(itemList.imageView)

            itemList.singleItem.setOnClickListener {
                onItemClick?.invoke(data)
            }
        }
    }

    fun setUserData(userData: List<UserData.Data>) {
        this.userData = userData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = SingleRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = userData[position]
        holder.bindUserData(data)
    }

    override fun getItemCount(): Int = userData.size
}
