package com.example.mvporder.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mvporder.MainActivity
import com.example.mvporder.R
import com.example.mvporder.UserMVP
import com.example.mvporder.databinding.Frag3Binding
import com.example.mvporder.model.Order

class FragmentOrderInfo:Fragment(R.layout.frag3) {

    private lateinit var order:Order
    private lateinit var binding: Frag3Binding
    private lateinit var presenter: UserMVP.Presenter

    companion object{
        @JvmStatic
        fun newInstance() =
            FragmentOrderInfo().apply {}
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       binding=Frag3Binding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            presenter = (activity as MainActivity?)!!.getPresenter()
            order = presenter.getOrder()
            binding.client.setText(order.user.login)
            binding.pass.setText(order.user.password)
            var drink = order.drink
            var type = order.type
            binding.orderText.setText("$drink $type")

            var moreRes:String = ""
            if(order.milk)  moreRes = "$moreRes молоко,"
            if(order.sugar) moreRes = "$moreRes сахар,"
            if(order.lemon) moreRes = "$moreRes лимон,"
            if(moreRes!="")moreRes = moreRes.substring(0,moreRes.length-1)

            binding.moreText.setText(moreRes)

        }

    }
}