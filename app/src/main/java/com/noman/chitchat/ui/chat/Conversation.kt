package com.noman.chitchat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentConversationBinding
import com.noman.chitchat.model.UserInfo

class Conversation : BaseFragmentWithBinding<FragmentConversationBinding>(
    FragmentConversationBinding::inflate
)
{
    private val args by navArgs<ConversationArgs>()
    private lateinit var result: UserInfo.UserInfoList
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         if(arguments != null){
             result = args.UserInfo
             setData(result)
         }
        binding.ivBack.setOnClickListener{
            findNavController().popBackStack()
        }
    }

    private fun setData(result: UserInfo.UserInfoList) {
        binding.txtUserName.text = result.name
        binding.txtActiveStatus.text = "Active"
        Glide.with(requireActivity()).load(result.image)
            .placeholder(R.drawable.google).into(binding.userIcon)

    }
}