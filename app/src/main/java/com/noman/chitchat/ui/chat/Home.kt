package com.noman.chitchat.ui.chat

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.noman.chitchat.R
import com.noman.chitchat.adapter.UserHorizontalListAdapter
import com.noman.chitchat.model.User
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentHomeBinding

class Home : BaseFragmentWithBinding<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
),UserHorizontalListAdapter.OnClickUserIcon
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        horizontalUserList()
    }

    private fun horizontalUserList() {
        binding.horizontalUserListRecyclerView.setHasFixedSize(true)
        binding.horizontalUserListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        val userList = mutableListOf<User.Users>()
        userList.add(User.Users("Afia","afia22@gmail.com","01728232832","kdsjfksdjfksj","kjsdkfjsdk"))
      /*  userList.add(User.Users(1,"Afia","afai22@gamilc.com","01718228277", R.drawable.google))
        userList.add(User.Users(2,"Orin","afai22@gamilc.com","01718228277",R.drawable.baseline_lock_reset_24))
        userList.add(User.Users(3,"Enni","afai22@gamilc.com","01718228277",R.drawable.baseline_arrow_back_24))
        userList.add(User.Users(4,"Tuna","afai22@gamilc.com","01718228277",R.drawable.google))
        userList.add(User.Users(5,"Afia","afai22@gamilc.com","01718228277", R.drawable.google))
        userList.add(User.Users(6,"Orin","afai22@gamilc.com","01718228277",R.drawable.baseline_lock_reset_24))
        userList.add(User.Users(7,"Enni","afai22@gamilc.com","01718228277",R.drawable.baseline_arrow_back_24))
        userList.add(User.Users(8,"Tuna","afai22@gamilc.com","01718228277",R.drawable.google))*/
        binding.horizontalUserListRecyclerView.layoutManager =
            LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding.horizontalUserListRecyclerView.adapter =
            UserHorizontalListAdapter(requireContext(),userList,this)

    }

    override fun onClickUserIcon(name: String) {
        Toast.makeText(requireContext(),"Name: $name",Toast.LENGTH_SHORT).show()
    }
}