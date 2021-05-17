package com.example.summary2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.summary2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val users = mutableListOf<User>()

    var removedUsers = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
    }

    private fun init() {
        binding.btnAdd.setOnClickListener {
            addUser()
        }

        binding.btnRemove.setOnClickListener {
            removeUser()
        }

        binding.btnUpdate.setOnClickListener {
            updateUser()
        }

        binding.btnOpen.setOnClickListener {
            moveToSecondActivity()
        }
    }

    private fun addUser() {
        if (areFieldsValid()) {
            val user = User(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etAge.text.toString().toInt(),
                binding.etEmail.text.toString()
            )

            if (users.contains(user)) {
                makeToast("User Already Exists")
                setActionText(false)
            } else {
                users.add(user)
                setActionText(true)

                binding.tvActiveUsersResult.text = users.size.toString()
                makeToast("User Added Successfully")
            }
        } else {
            makeToast("Fill All Fields")
            setActionText(false)
        }
    }

    private fun removeUser() {
        if (areFieldsValid()) {
            val user = User(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etAge.text.toString().toInt(),
                binding.etEmail.text.toString()
            )

            if (users.contains(user)) {
                users.remove(user)
                makeToast("User Deleted Successfully")
                setActionText(true)

                removedUsers++
                binding.tvRemovedUsersResult.text = removedUsers.toString()
                binding.tvActiveUsersResult.text = users.size.toString()
            } else {
                makeToast("User Doesn't Exists")
                setActionText(false)
            }
        } else {
            makeToast("Fill All Fields")
            setActionText(false)
        }
    }

    private fun updateUser() {
        if (areFieldsValid()) {
            val user = User(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etAge.text.toString().toInt(),
                binding.etEmail.text.toString()
            )

            var userHasChanged = false

            users.forEachIndexed { index, element ->
                if (element.email == user.email) {
                    users[index] = user
                    userHasChanged = true
                }
            }

            if (userHasChanged) {
                makeToast("User Info with ${user.email} has been updated")
                setActionText(true)
            } else {
                makeToast("User with ${user.email} isn't registered")
                setActionText(false)
            }
        } else {
            makeToast("Fill All Fields")
            setActionText(false)
        }
    }

    private fun moveToSecondActivity() {
        if (areFieldsValid()) {
            val user = User(
                binding.etFirstName.text.toString(),
                binding.etLastName.text.toString(),
                binding.etAge.text.toString().toInt(),
                binding.etEmail.text.toString()
            )
            users.add(user)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("user", user)
            startActivity(intent)
        } else {
            makeToast("Fill all fields")
        }
    }

    private fun areFieldsValid(): Boolean {
        return binding.etFirstName.text.isNotEmpty() &&
                binding.etLastName.text.isNotEmpty() &&
                binding.etAge.text.isNotEmpty() &&
                binding.etEmail.text.isNotEmpty()
    }

    private fun setActionText(isSuccess: Boolean) {
        if (isSuccess) {
            binding.tvActionResult.text = "Success"
            binding.tvActionResult.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.success_color
                )
            )
        } else {
            binding.tvActionResult.text = "Error"
            binding.tvActionResult.setTextColor(
                ContextCompat.getColor(
                    this,
                    R.color.error_color
                )
            )
        }
    }

    private fun makeToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
