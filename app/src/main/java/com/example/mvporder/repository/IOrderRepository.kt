package com.example.mvporder.repository

import com.example.mvporder.model.Order
import com.example.mvporder.model.User

interface IOrderRepository {
    fun saveOrder(user: User, drink: String, type: String, sugar: Boolean, milk: Boolean, lemon: Boolean)
    fun getOrder(): Order
}