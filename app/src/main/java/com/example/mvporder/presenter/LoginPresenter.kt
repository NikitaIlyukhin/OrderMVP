package com.example.mvporder.presenter

import androidx.fragment.app.Fragment
import com.example.mvporder.UserMVP
import com.example.mvporder.UserMVP.Presenter
import com.example.mvporder.R
import com.example.mvporder.model.Order
import com.example.mvporder.model.User


class LoginPresenter(private val model: UserMVP.UserModel) :Presenter{
    private var view: UserMVP.View? = null
    private var user:User? = null
    private var fragment:Fragment? = null
    private var nextFragment:Fragment? = null
    private var order:Order? = null

    override fun setView(view: UserMVP.View) {
        this.view=view
    }
    override fun setFragment(fragment:Fragment) {
        this.fragment=fragment
    }
    override fun setNextFragment(fragment:Fragment) {
        this.nextFragment=fragment
    }

    override fun makeOrderButtonClicked() {
        if (view != null) {
                model.saveUser(view!!.getLogin(),view!!.getPassword())
                goToNextFragment(fragment!!, nextFragment!!)
        }
    }

    override fun setCurrentUser() {
        this.user = model.getUser()
    }

    override fun goToNextFragment(fragment: Fragment,nextFragment:Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view,nextFragment)
            .commit()
    }
    override fun getCurrentUser():User{
        return model.getUser()
    }

    override fun setOrderView(view: UserMVP.OrderView) {
        //
    }

    override fun getOrder():Order {
        return this.order!!
    }
}