package com.smartherd.globofly.models

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import silicon.thirumanam.com.thirumanam.LoginFragment
import silicon.thirumanam.com.thirumanam.Common.ServiceBuilder
import silicon.thirumanam.com.thirumanam.Common.WebService

class UserRepository(){
   private val service: WebService = ServiceBuilder.buildService(WebService::class.java)

    fun getUser(id:String, password:String,user: MutableLiveData<User>){
        Log.d("!!!!!!!!!!!!!!!", " $id $password")
        var data: MutableLiveData<User>?=null
        val filterLogin= HashMap<String,String>()
        filterLogin["id"]=id
        filterLogin["password"]=password

        val loginRequestCall: Call<User> = service.login(filterLogin)

        loginRequestCall.enqueue(object : Callback<User>{

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.d(LoginFragment.TAG, "" + "Not Authorised user")
            }
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    if(response.code()==200) {
                        user.value=response.body()!!
                        Log.d(LoginFragment.TAG, "" + "Authorised user")
                        Log.d("******* 7","response.code()==200")
                    }
                }else {
                    val result = when (response.code()) {
                        404 ->  "Invalid Id and/or Password"
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()

                    }

                }
            }
        })

    }
}
