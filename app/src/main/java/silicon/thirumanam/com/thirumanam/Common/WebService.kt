package silicon.thirumanam.com.thirumanam.Common

import android.arch.lifecycle.MutableLiveData
import com.smartherd.globofly.models.User
import retrofit2.Call
import retrofit2.http.*

interface WebService{


    @POST("user")
    fun registerUser(@Body newUser: User): Call<User>


    @POST("login")
    fun login(@QueryMap filter: HashMap<String, String>): Call<User>


    @POST("users")
    fun getMatches(@QueryMap filter: HashMap<String, String>): Call<List<User>>


    @PUT("user")
    fun updateUser(@Body user: User): Call<User>


    @POST("userDetail")
    fun getUser(@QueryMap filter: HashMap<String, String>): Call<User>


    @DELETE("deleteUser")
    fun deleteUser(@QueryMap filter: HashMap<String, String>): Call<Unit>

}