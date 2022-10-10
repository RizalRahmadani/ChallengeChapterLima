package com.example.challengechapterlima.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.challengechapterlima.R
import com.example.challengechapterlima.adapter.UserAdapter
import com.example.challengechapterlima.databinding.ActivityLoginBinding
import com.example.challengechapterlima.fragment.MovieFragment
import com.example.challengechapterlima.model.ResponseDataUserItem
import com.example.challengechapterlima.service.RetrofitUser
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class LoginActivity : AppCompatActivity() {

    lateinit var binding : ActivityLoginBinding
    lateinit var sharedpref : SharedPreferences
    lateinit var adapter : UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.IN.setOnClickListener {
            setLocale("id")
        }

        binding.EN.setOnClickListener {
            setLocale("en")
        }


        binding.btnLogin.setOnClickListener {
            //data inputan user
            var inputUsername = binding.editUsernameLog.text.toString()
            var inputPass = binding.editPasswordLog.text.toString()

            if(inputUsername != null && inputPass !=null) requestLogin(inputUsername, inputPass)
            else if(inputUsername == null && inputPass == null) toast("Empty Username or Password!")
        }

        binding.txtRegister.setOnClickListener{
            val pindah = Intent(this, RegisterActivity::class.java)
            startActivity(pindah)
        }

        // kalo gada sharedpref, masuk ke login trus dicek datanya.
        // kalo gada di api, gagal masuk harus regis dulu.
        // di register input data ke api trus back to login.
        // kalo ada di api, ntar otomatis kesimpen di shared trus masuk ke home.
    }

    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_LONG).show()
    }

    fun requestLogin(username:String, password:String){
        RetrofitUser.instance.getAllUser().enqueue(object :
            Callback<List<ResponseDataUserItem>> {
            override fun onResponse(call: Call<List<ResponseDataUserItem>>, response: Response<List<ResponseDataUserItem>>) {
                var data = false
                if(response.isSuccessful){
                    if(response.body() != null){
                        val respon = response.body()
                        for (i in 0 until respon!!.size){
                            if(respon[i].username.equals(username) && respon[i].password.equals(password)){
                                data = true

                                //add ke sharedpref
                                var addUser = sharedpref.edit()
                                addUser.putString("username", username)
                                addUser.putString("password", password)
                                addUser.apply()

                                toast("Login Success!")
                                var pinda = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(pinda)
                            }
                        }
                        if(data == false) toast("Wrong Username or Password!")
                    }
                    else toast("Empty Response!")
                }
                else toast("Failed Load Data!")
            }

            override fun onFailure(call: Call<List<ResponseDataUserItem>>, t: Throwable) {
//                Toast.makeText(this, "Registration Success!", Toast.LENGTH_SHORT).show()
//                Toast.makeText(this@LoginActivity, "Login gagal", Toast.LENGTH_SHORT).show()
            }

        })
    }

    fun setLocale(lang: String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}