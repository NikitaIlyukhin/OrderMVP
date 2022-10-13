package com.example.mvporder.presenter

import androidx.fragment.app.Fragment
import com.example.mvporder.R
import com.example.mvporder.UserMVP
import com.example.mvporder.model.Order
import com.example.mvporder.model.User

class OrderPresenter(private val model: UserMVP.OrderModel):UserMVP.Presenter {
    private var view: UserMVP.OrderView? = null
    private var user:User? = null
    private var fragment:Fragment? = null
    private var nextFragment:Fragment? = null

    override fun setOrderView(view: UserMVP.OrderView) {
        this.view=view
    }

    override fun setView(view: UserMVP.View) {
       //
    }

    override fun makeOrderButtonClicked() {
        if (view != null) {
            model.saveOrder(this.user!!, view!!.getDrink()!!,
                view!!.getDrinkType()!!,view!!.getSugar(),view!!.getMilk(),view!!.getLemon())
            goToNextFragment(fragment!!, nextFragment!!)
        }
    }

    override fun setCurrentUser() {
        user = model.getOrder().user
    }
    fun setCurrentUser(user:User){
        this.user = user
    }

    override fun getCurrentUser(): User {
        return model.getOrder().user
    }

    override fun goToNextFragment(fragment: Fragment, nextFragment: Fragment) {
        fragment.parentFragmentManager
            .beginTransaction()
            .addToBackStack(null)
            .replace(R.id.fragment_container_view,nextFragment)
            .commit()
    }

    override fun setFragment(fragment: Fragment) {
        this.fragment=fragment
    }

    override fun setNextFragment(fragment: Fragment) {
        this.nextFragment=fragment
    }

    override fun getOrder(): Order {
        return model.getOrder()
    }
}