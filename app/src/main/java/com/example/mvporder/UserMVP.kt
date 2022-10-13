package com.example.mvporder

import androidx.fragment.app.Fragment
import com.example.mvporder.model.Order
import com.example.mvporder.model.User

interface UserMVP {
    interface View {
        fun getLogin(): String
        fun getPassword(): String
        fun setPassword(password: String?)
        fun setLogin(login: String?)
    }
    interface OrderView{
        fun getDrink():String?
        fun getDrinkType():String?
        fun getMilk():Boolean
        fun getSugar():Boolean
        fun getLemon():Boolean
    }

    interface Presenter {
        fun setView(view: View)
        fun setOrderView(view:OrderView)
        fun makeOrderButtonClicked()
        fun setCurrentUser()
        fun getCurrentUser():User
        fun goToNextFragment(fragment: Fragment,nextFragment:Fragment)
        fun setFragment(fragment:Fragment)
        fun setNextFragment(fragment:Fragment)
        fun getOrder():Order
    }

    interface UserModel {
        fun saveUser(login: String, password: String)
        fun getUser(): User
    }
    interface OrderModel{
        fun saveOrder(user:User, drink: String,type: String,sugar: Boolean, milk: Boolean, lemon: Boolean)
        fun getOrder():Order
    }
}