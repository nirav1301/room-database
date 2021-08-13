package com.example.roomdatabase.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomdatabase.R
import com.example.roomdatabase.data.User
import com.example.roomdatabase.data.UserAdapter
import com.example.roomdatabase.data.UserViewModel
import com.example.roomdatabase.databinding.FragmentListBinding
import easyadapter.dc.com.library.EasyAdapter.OnRecyclerViewItemClick


class ListFragment : Fragment() {
    private var mAdpater : UserAdapter ?= null
    private lateinit var mUserViewModel : UserViewModel
    lateinit var binding : FragmentListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_list, container, false)
        binding.floatingActionButton.setOnClickListener({
            findNavController().navigate(R.id.action_listFragment_to_addUserFragment)
        }

        )
        init()



        return binding.root
    }
fun init(){
    mAdpater = UserAdapter()
    val layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    binding.userList.setLayoutManager(layoutManager)
    binding.userList.setItemAnimator(DefaultItemAnimator())
    binding.userList.setAdapter(mAdpater)
    mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
    mUserViewModel.readAllData.observe(requireActivity(), Observer {user ->
        mAdpater!!.clear(false)
        mAdpater!!.addAll(user,false)
        Toast.makeText(requireContext(), "Data adeed", Toast.LENGTH_SHORT).show()
        mAdpater!!.notifyDataSetChanged()
    })
    mAdpater!!.setRecyclerViewItemClick(OnRecyclerViewItemClick<User> { view, model ->
        var selectedSubject = model
        var userId = selectedSubject.id.toString()
        var firstName = selectedSubject.firstName
        var lastName = selectedSubject.lastName
        var age = selectedSubject.age.toString()
        var intent = Intent(requireContext(),UpdateFragment::class.java)
        intent.putExtra("id",userId)
        intent.putExtra("firstname",firstName)
        intent.putExtra("lastname",lastName)
        intent.putExtra("age",age)
        findNavController().navigate(R.id.action_listFragment_to_updateFragment)

    })

}


}