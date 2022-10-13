package com.example.mvporder.model

import com.example.mvporder.UserMVP
import com.example.mvporder.repository.LoginRepository

class LoginModel(private val repository: LoginRepository) : UserMVP.UserModel {
    override fun saveUser(login: String, password: String) {
        repository.saveUser(User(login,password))
    }

    override fun getUser(): User {
       return repository.getUser()
    }
}