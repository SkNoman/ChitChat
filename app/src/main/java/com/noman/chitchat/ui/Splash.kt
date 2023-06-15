package com.noman.chitchat.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentSplashBinding

class Splash : BaseFragmentWithBinding<FragmentSplashBinding>
    (FragmentSplashBinding::inflate)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.demoTxt.text = "Hello This is splash screeen"
        binding.demoTxt.setOnClickListener {
            findNavController().navigate(R.id.login)
        }
    }
}