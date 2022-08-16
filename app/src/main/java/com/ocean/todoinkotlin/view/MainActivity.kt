package com.ocean.todoinkotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.ocean.todoinkotlin.R
import com.ocean.todoinkotlin.databinding.ActivityMainBinding
import com.ocean.todoinkotlin.view.fragment.AddTODOFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        
    }

    fun goToAddToDoFrag(view: View) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout_for_fragment, AddTODOFragment()).commit()
        binding.faBtnGoAddTodo.isVisible = false
    }
}