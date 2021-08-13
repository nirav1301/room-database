package com.example.roomdatabase.ui.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.roomdatabase.R
import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserViewModel
import com.example.roomdatabase.databinding.FragmentAddUserBinding

class AddUserFragment : Fragment() {
    lateinit var binding : FragmentAddUserBinding
    lateinit var mViewUserModel : UserViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(activity, "User List", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_add_user, container, false)
        mViewUserModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.btnAdduser.setOnClickListener({
            val firstName = binding.userFirstName.text.toString()
            val lastName = binding.userLastName.text.toString()
            val age = binding.userAge.text.toString()
            if(firstName.isEmpty()){
                binding.userFirstName.requestFocus()
                binding.userFirstName.setError("Please Enter First Name")
            }
            else if(lastName.isEmpty()){
                binding.userLastName.requestFocus()
                binding.userLastName.setError("Please Enter Last Name")
            }
            else if(age.isEmpty()){
                binding.userAge.requestFocus()
                binding.userAge.setError("Please Enter age")
            }
            else{
                insertDataToDatabse()
            }




        })
        return binding.root
    }

    private fun insertDataToDatabse() {
        val firstName = binding.userFirstName.text.toString()
        val lastName = binding.userLastName.text.toString()
        val age = binding.userAge.text
        val user = User(0,firstName,lastName,Integer.parseInt(age.toString()))
        mViewUserModel.addUSer(user)
        Toast.makeText(requireContext(), "User Added SuccessFully", Toast.LENGTH_SHORT).show()

    }


}