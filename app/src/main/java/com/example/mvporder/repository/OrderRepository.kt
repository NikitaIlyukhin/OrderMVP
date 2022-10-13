package com.example.mvporder.repository

import com.example.mvporder.model.Order
import com.example.mvporder.model.User

class OrderRepository:IOrderRepository {
    private var order: Order? = null
    override fun saveOrder(
        user: User,
        drink: String,
        type: String,
        sugar: Boolean,
        milk: Boolean,
        lemon: Boolean
    ) {
        order = Order(user,drink,type,sugar,milk,lemon)
    }

    override fun getOrder(): Order {
        if(order!=null)
            return this.order!!
        else
            return Order(User("Test","123"),"","",false,false,false)
    }
}