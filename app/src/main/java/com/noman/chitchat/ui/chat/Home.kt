package com.noman.chitchat.ui.chat

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.noman.chitchat.R
import com.noman.chitchat.adapter.MainChatUserListAdapter
import com.noman.chitchat.adapter.UserHorizontalListAdapter
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentHomeBinding
import com.noman.chitchat.model.UserInfo

class Home : BaseFragmentWithBinding<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
),UserHorizontalListAdapter.OnClickUserIcon,
        MainChatUserListAdapter.OnClickUser
{
    private lateinit var auth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    private var userInfoList  =  mutableListOf<UserInfo.UserInfoList>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        dbRef = FirebaseDatabase.getInstance().reference


        dbRef.child("users").addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                userInfoList.clear()
                for (postSnapshot in snapshot.children){
                    val currentUser = postSnapshot.getValue(UserInfo.UserInfoList::class.java)
                    if (auth.currentUser?.uid != currentUser?.uid){
                        userInfoList.add(currentUser!!)
                    }
                }
                horizontalUserList(userInfoList)
                verticalUserList(userInfoList)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        binding.ivLogout.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.login)
        }
    }

    private fun verticalUserList(userInfoList: MutableList<UserInfo.UserInfoList>) {
        binding.mainChatRecyclerView.setHasFixedSize(true)
        binding.mainChatRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )

        binding.mainChatRecyclerView.layoutManager =
            LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        binding.mainChatRecyclerView.adapter =
            MainChatUserListAdapter(requireContext(),userInfoList,this)
    }

    private fun horizontalUserList(userInfoList: MutableList<UserInfo.UserInfoList>) {
        binding.horizontalUserListRecyclerView.setHasFixedSize(true)
        binding.horizontalUserListRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )


        binding.horizontalUserListRecyclerView.layoutManager =
            LinearLayoutManager(activity,LinearLayoutManager.HORIZONTAL,false)
        binding.horizontalUserListRecyclerView.adapter =
            UserHorizontalListAdapter(requireContext(),userInfoList,this)


    }

    override fun onClickUserIcon(name: String) {
        Toast.makeText(requireContext(),"Name: $name",Toast.LENGTH_SHORT).show()
    }

    override fun onClickUser(userInfo: UserInfo.UserInfoList) {
        //Toast.makeText(requireContext(),"Name: ${userInfo.name}",Toast.LENGTH_SHORT).show()
        val action =HomeDirections.actionHome2ToConversation(userInfo)
        findNavController().navigate(action)
    }
}