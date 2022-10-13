package com.example.mvporder.model

import com.example.mvporder.UserMVP
import com.example.mvporder.repository.OrderRepository

class OrderModel(private val repository: OrderRepository):UserMVP.OrderModel {
    override fun saveOrder(
        user:User,
        drink: String,
        type: String,
        sugar: Boolean,
        milk: Boolean,
        lemon: Boolean
    ) {
        repository.saveOrder(user,drink,type,sugar,milk,lemon)
    }

    override fun getOrder(): Order {
        return repository.getOrder()
    }
}