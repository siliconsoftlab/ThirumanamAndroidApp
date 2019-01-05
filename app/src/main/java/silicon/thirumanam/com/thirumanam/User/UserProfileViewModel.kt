package silicon.thirumanam.com.thirumanam.User

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.smartherd.globofly.models.User
import com.smartherd.globofly.models.UserRepository

class UserProfileViewModel(): ViewModel() {
    private lateinit var user: MutableLiveData<User>
    private val userRepository=UserRepository()

    fun getUser(id:String, password:String): LiveData<User>{

        user =MutableLiveData<User>()
        userRepository.getUser(id,password,user)
        return user
    }

}