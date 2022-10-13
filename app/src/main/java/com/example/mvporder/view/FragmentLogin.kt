package com.example.mvporder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.mvporder.UserMVP.Presenter
import com.example.mvporder.databinding.ActivityMainBinding
import com.example.mvporder.model.LoginModel
import com.example.mvporder.presenter.LoginPresenter
import com.example.mvporder.repository.UserRepository
import com.example.mvporder.databinding.Frag1Binding


class FragmentLogin:Fragment(R.layout.frag1),UserMVP.View {
    private lateinit var login:EditText
    private lateinit var binding : Frag1Binding
    private lateinit var password:EditText
    private lateinit var presenter: Presenter
    private lateinit var button:Button

    companion object {
        @JvmStatic
        fun newInstance() =
            FragmentLogin().apply {}
    }

    override fun getLogin(): String {

        return login.text.toString()
    }
    override fun getPassword(): String {
        return password.text.toString()
    }

    override fun setPassword(password: String?) {
        if(password == null)
            this.password.setText("111")
        else
            this.password.setText(password)
    }

    override fun setLogin(login: String?) {
        if(login == null)
            this.login.setText("User")
        else
            this.login.setText(login)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
            binding = Frag1Binding.inflate(inflater)
            return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(savedInstanceState == null){
            login = binding.loginField
            password = binding.passwordField

            presenter = LoginPresenter(LoginModel(UserRepository()))
            (activity as MainActivity?)!!.setPresenter(presenter)
            presenter.setView(this)
            presenter.setFragment(this)

            var fragmentOrder: FragmentOrder = FragmentOrder.newInstance()
            presenter.setNextFragment(fragmentOrder)

            binding.button.setOnClickListener { presenter!!.makeOrderButtonClicked() }}
    }
}