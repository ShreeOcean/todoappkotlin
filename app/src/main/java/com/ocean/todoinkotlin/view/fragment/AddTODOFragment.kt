package com.ocean.todoinkotlin.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ocean.todoinkotlin.R
import com.ocean.todoinkotlin.databinding.FragAddTodoBinding

class AddTODOFragment : Fragment() {

    private lateinit var binding: FragAddTodoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragAddTodoBinding.inflate(inflater,container,false)
        return binding.root
    }

}