package com.noman.chitchat.ui.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.noman.chitchat.R
import com.noman.chitchat.base.BaseFragmentWithBinding
import com.noman.chitchat.databinding.FragmentHomeBinding

class Home : BaseFragmentWithBinding<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
)
{
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.demoTxtHome.text = "This Is HOME"
    }
}