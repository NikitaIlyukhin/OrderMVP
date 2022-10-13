package com.example.mvporder.repository

import com.example.mvporder.model.User

interface LoginRepository {
    fun getUser(): User
    fun saveUser(user:User?)
}