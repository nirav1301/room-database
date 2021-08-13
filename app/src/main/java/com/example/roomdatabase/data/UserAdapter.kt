package com.example.roomdatabase.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdatabase.R
import com.example.roomdatabase.databinding.ItemUserDataBinding
import easyadapter.dc.com.library.EasyAdapter

class UserAdapter : EasyAdapter<User,ItemUserDataBinding>(R.layout.item_user_data){
    override fun onBind(item: ItemUserDataBinding, user: User) {
        item.userId.setText(user.id.toString())
        item.tvUserFirstName.setText(user.firstName.toString())
        item.tvUserLastName.setText(user.lastName.toString())
        item.tvUserAge.setText(user.age.toString())
    }

    override fun onCreatingHolder(binding: ItemUserDataBinding, holder: EasyHolder) {
        super.onCreatingHolder(binding, holder)
        binding.getRoot().setOnClickListener(holder.getClickListener());
    }


}