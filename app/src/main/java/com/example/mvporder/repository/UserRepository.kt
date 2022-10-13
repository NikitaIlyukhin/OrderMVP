package com.example.mvporder.repository

import com.example.mvporder.model.User

class UserRepository :LoginRepository{
    private var user:User? = null
    override fun getUser(): User {
        return if (user == null) {
            User("N", "I")
        } else {
            this.user!!
        }
    }

    override fun saveUser(user: User?) {
        var user: User? = user
        if (user == null) {
            user = getUser()
        }
        this.user = user
    }
}