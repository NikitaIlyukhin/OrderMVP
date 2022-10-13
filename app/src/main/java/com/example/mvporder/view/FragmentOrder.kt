package com.example.mvporder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.mvporder.databinding.Frag2Binding
import com.example.mvporder.model.OrderModel
import com.example.mvporder.model.User
import com.example.mvporder.presenter.OrderPresenter
import com.example.mvporder.repository.OrderRepository
import com.example.mvporder.view.FragmentOrderInfo

class FragmentOrder:Fragment(R.layout.frag2),UserMVP.OrderView{
    private val teaArray:Array<String> = arrayOf("Зеленый", "Черный")
    private val coffeeArray = arrayOf("Американо","Капуччино")

    private lateinit var presenter: UserMVP.Presenter
    private lateinit var binding: Frag2Binding

    private lateinit var login:String
    private var drink:String = ""
    private var type:String = ""

    private lateinit var loginField:TextView
    private lateinit var lemonlbl:TextView
    private lateinit var drinkFieldGroup:RadioGroup
    private lateinit var selectedDrink: RadioButton
    private lateinit var milkCB:CheckBox
    private lateinit var lemonCB:CheckBox
    private lateinit var sugarCB:CheckBox
    private lateinit var spinnerTea:Spinner
    private lateinit var spinnerCoffee:Spinner
    private lateinit var user: User

    companion object{
        @JvmStatic
        fun newInstance() =
            FragmentOrder().apply {}
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {
        binding = Frag2Binding.inflate(inflater)
        return binding.root
    }

    override fun getDrink(): String? {
        return drink
    }

    override fun getDrinkType(): String? {
        return type
    }

    override fun getMilk(): Boolean {
        return binding.checkBoxMilk.isChecked
    }

    override fun getSugar(): Boolean {
        return binding.checkBoxSugar.isChecked
    }

    override fun getLemon(): Boolean {
        return binding.checkBoxLemon.isChecked
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(savedInstanceState == null){
            drinkFieldGroup = binding.radioGroup    //view.findViewById(R.id.radioGroup)
            milkCB          = binding.checkBoxMilk  //view.findViewById(R.id.checkBoxMilk)
            lemonlbl        = binding.lemon
            lemonCB         = binding.checkBoxLemon //view.findViewById(R.id.checkBoxLemon)
            sugarCB         = binding.checkBoxSugar //view.findViewById(R.id.checkBoxSugar)
            loginField      = binding.hello         //view.findViewById(R.id.hello)
            spinnerTea      = binding.spinnerTea    //view.findViewById(R.id.spinnerTea)
            spinnerCoffee   = binding.spinnerCoffee //view.findViewById(R.id.spinnerCoffee)

            presenter = (activity as MainActivity?)!!.getPresenter()
            user = presenter.getCurrentUser()
            login = user.login
            loginField.setText("Привет, $login")
            presenter = OrderPresenter(OrderModel(OrderRepository()))
            (activity as MainActivity?)!!.setPresenter(presenter)
            presenter.setOrderView(this)
            presenter.setFragment(this)
            var fragmentOrderInfo: FragmentOrderInfo = FragmentOrderInfo.newInstance()
            presenter.setNextFragment(fragmentOrderInfo)
            (presenter as OrderPresenter).setCurrentUser(user)

            //Блок ЧАЙ - Кофе
            selectedDrink = view.findViewById(drinkFieldGroup.checkedRadioButtonId)
            drink = selectedDrink.text.toString()

            if(drink == "Чай"){
                spinnerCoffee.visibility    = View.GONE
                spinnerTea.visibility       = View.VISIBLE
            } else {
                spinnerTea.visibility       = View.GONE
                spinnerCoffee.visibility    = View.VISIBLE
            }

            //Изменения в радио чай-кофе
            drinkFieldGroup.setOnCheckedChangeListener { group, checkedId ->
                selectedDrink = view.findViewById(checkedId)
                drink = selectedDrink.text.toString()

                //Скрыть "добавить лимон" если не чай
                if(drink != "Чай") {
                    lemonlbl.visibility         = View.GONE
                    lemonCB.visibility          = View.GONE
                    spinnerTea.visibility       = View.GONE
                    spinnerCoffee.visibility    = View.VISIBLE
                } else {
                    lemonlbl.visibility         = View.VISIBLE
                    lemonCB.visibility          = View.VISIBLE
                    spinnerCoffee.visibility    = View.GONE
                    spinnerTea.visibility       = View.VISIBLE
                }
            }

            //SpinnerTea
            spinnerTea?.adapter = ArrayAdapter(activity?.applicationContext!!, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, teaArray)
            spinnerTea?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    type = parent?.getItemAtPosition(position).toString()
                }
            }
            //SpinnerCoffee
            spinnerCoffee?.adapter = ArrayAdapter(activity?.applicationContext!!, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, coffeeArray)
            spinnerCoffee?.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    type = parent?.getItemAtPosition(position).toString()
                }
            }

            //Кнопка сделать заказ
            binding.button.setOnClickListener {
                presenter.makeOrderButtonClicked()
            }
        }
    }

}
