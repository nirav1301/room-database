package com.example.roomdatabase.data

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {
    val readAllData : LiveData<List<User>> = userDao.readData()

   fun addUser(user: User){
        userDao.addUser(user)
    }
}