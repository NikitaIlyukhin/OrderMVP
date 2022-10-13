package com.example.mvporder.model

class Order(val user:User, val drink: String,val type: String,
            val sugar: Boolean, val milk: Boolean, val lemon: Boolean) {
}