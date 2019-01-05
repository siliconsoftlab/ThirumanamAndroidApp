package com.smartherd.globofly.models

import android.arch.lifecycle.MutableLiveData
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import silicon.thirumanam.com.thirumanam.LoginFragment
import silicon.thirumanam.com.thirumanam.Common.ServiceBuilder
import silicon.thirumanam.com.thirumanam.Common.WebService

class MatchesRepository(){
   private val service: WebService = ServiceBuilder.buildService(WebService::class.java)


     fun getMatches(filterMatches:HashMap<String,String>,users: MutableLiveData<List<User>>){
        val service: WebService = ServiceBuilder.buildService(WebService::class.java)

        val loginRequestCall: Call<List<User>> = service.getMatches(filterMatches)
        loginRequestCall.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Log.d(LoginFragment.TAG, "" + "Could not get data")
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    val res:List<User> = response.body()!!
                    users.value= response.body()!!
                    Log.d(LoginFragment.TAG,""+res.toString())

                }else {
                    val result = when (response.code()) {
                        404 ->  "Users not found for your query "+ response.code()
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()
                    }

                }
            }
        })
    }

}
