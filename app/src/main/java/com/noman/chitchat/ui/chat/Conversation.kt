package com.noman.chitchat.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.noman.chitchat.R
import com.noman.chitchat.adapter.MessageAdapter
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentConversationBinding
import com.noman.chitchat.model.Message
import com.noman.chitchat.model.UserInfo

class Conversation : BaseFragmentWithBinding<FragmentConversationBinding>(
    FragmentConversationBinding::inflate
)
{
    private val args by navArgs<ConversationArgs>()
    private lateinit var result: UserInfo.UserInfoList
    private lateinit var messageAdapter: MessageAdapter
    private lateinit var messageList: ArrayList<Message>

    private lateinit var mDbRef: DatabaseReference

    var senderRoom:String? = null
    var receiverRoom:String? =null
    var senderUserImage:String? =null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivBack.setOnClickListener{
            findNavController().popBackStack()
        }
         if(arguments != null){
             result = args.UserInfo
             setData(result)
         }


        val receiverUid = result.uid
        val senderUid = FirebaseAuth.getInstance().currentUser?.uid
        mDbRef = FirebaseDatabase.getInstance().reference

        mDbRef.child("users").child("$senderUid").addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    val userInfo = snapshot.getValue(UserInfo.UserInfoList::class.java)
                    senderUserImage = userInfo!!.image
                    Log.e("nlog",senderUserImage!!)

                } else {
                   Toast.makeText(requireContext(),"Something went wrong",Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle any errors that occur during the data retrieval
                Log.e("Firebase", "Data retrieval cancelled: ${error.message}")
            }
        })

        senderRoom = receiverUid + senderUid
        receiverRoom = senderUid + receiverUid


        messageList = ArrayList()
        messageAdapter = MessageAdapter(requireContext(),messageList)
        binding.chatRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.chatRecyclerView.adapter = messageAdapter

        //LOGIC TO SHOW DATA IN VIEW
        mDbRef.child("chats").child(senderRoom!!).child("messages")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {

                    messageList.clear()
                    for (postSnapShot in snapshot.children){
                        val message = postSnapShot.getValue(Message::class.java)
                        messageList.add(message!!)
                    }
                    messageAdapter.notifyDataSetChanged()
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }

            })

        //ADDING THE MESSAGE TO DB
        binding.ivSentMessage.setOnClickListener{
            val message  = binding.etMessageInput.text.toString()
            Log.e("nlog-image",result.image.toString())
            val messageObject = Message(message,senderUid,senderUserImage)

            mDbRef.child("chats").child(senderRoom!!).child("messages").push()
                .setValue(messageObject).addOnSuccessListener {
                    mDbRef.child("chats").child(receiverRoom!!).child("messages").push()
                        .setValue(messageObject)
                }
            binding.etMessageInput.setText("")
        }
    }

    private fun setData(result: UserInfo.UserInfoList) {
        binding.txtUserName.text = result.name
        binding.txtActiveStatus.text = "Active"
        Glide.with(requireActivity()).load(result.image)
            .placeholder(R.drawable.google).into(binding.userIcon)

    }
}