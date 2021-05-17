package com.example.summary2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.summary2.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
    }

    private fun getData() {
        val data = intent.getParcelableExtra<User>("user")

        binding.tvFirstName.text = data?.firstName
        binding.tvLastName.text = data?.lastName
        binding.tvAge.text = data?.age.toString()
        binding.tvEmail.text = data?.email
    }
}