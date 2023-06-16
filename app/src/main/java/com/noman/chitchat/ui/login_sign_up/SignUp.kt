package com.noman.chitchat.ui.login_sign_up

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentSignUpBinding

class SignUp : BaseFragmentWithBinding<FragmentSignUpBinding>(
    FragmentSignUpBinding::inflate
)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLoginBack.setOnClickListener{
            findNavController().navigate(R.id.login)
        }
    }
}