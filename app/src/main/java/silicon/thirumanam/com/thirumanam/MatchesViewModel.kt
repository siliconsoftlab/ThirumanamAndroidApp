package silicon.thirumanam.com.thirumanam

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.smartherd.globofly.models.MatchesRepository
import com.smartherd.globofly.models.User
import com.smartherd.globofly.models.UserRepository

class MatchesViewModel(): ViewModel() {
    private lateinit var users: MutableLiveData<List<User>>
    private val matchesRepository=MatchesRepository()

    fun getMatches(): MutableLiveData<List<User>>{
        val filterMatches= HashMap<String,String>()
        filterMatches["sex"]="Female"
        users =MutableLiveData<List<User>>()
        matchesRepository.getMatches(filterMatches,users);
        return users
    }

}