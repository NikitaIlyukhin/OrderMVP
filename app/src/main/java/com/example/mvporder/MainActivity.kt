package com.example.mvporder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    private lateinit var transaction: FragmentTransaction
    private lateinit var fragmentLogin: FragmentLogin
    private lateinit var presenter: UserMVP.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if(savedInstanceState == null){
            transaction = supportFragmentManager.beginTransaction()
            fragmentLogin = FragmentLogin.newInstance()
            transaction.add(R.id.fragment_container_view,fragmentLogin,"loginFragment").commit()
        }
    }
    fun setPresenter(presenter:UserMVP.Presenter){
        this.presenter = presenter
    }
    fun getPresenter():UserMVP.Presenter{
        return presenter
    }
}