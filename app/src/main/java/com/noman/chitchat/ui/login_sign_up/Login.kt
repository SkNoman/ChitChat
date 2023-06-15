package com.noman.chitchat.ui.login_sign_up

import android.os.Bundle
import android.view.View
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentLoginBinding


class Login : BaseFragmentWithBinding<FragmentLoginBinding>
    (FragmentLoginBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.demoTxtLogin.text = "This is login fragment"
    }
}