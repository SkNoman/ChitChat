package com.noman.chitchat.ui.login_sign_up

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentLoginBinding


private lateinit var mAuth: FirebaseAuth
class Login : BaseFragmentWithBinding<FragmentLoginBinding>
    (FragmentLoginBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRegister.setOnClickListener{
            findNavController().navigate(R.id.signUp)
        }
        mAuth = FirebaseAuth.getInstance()

        binding
    }
}