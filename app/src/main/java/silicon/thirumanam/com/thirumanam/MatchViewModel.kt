package silicon.thirumanam.com.thirumanam

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.smartherd.globofly.models.User

class MatchViewModel: ViewModel() {
    private lateinit var matches:MutableLiveData<List<User>>
    private lateinit var match:MutableLiveData<User>

    fun getMatches(): LiveData<List<User>> {
        if(!::matches.isInitialized){
            matches= MutableLiveData();
        }
        return matches
    }

    fun setMatches(matchesList:List<User>){
        if(!::matches.isInitialized){
            matches= MutableLiveData();
        }
        Log.d("############","Match size is ${matchesList.size}")
        matches.value=matchesList
    }

    fun getMatch(): LiveData<User> {
        if(!::match.isInitialized){
            match= MutableLiveData();
          // loadMatches()
        }
        return match
    }

    fun setMatch( matchModel: User){
        if(!::match.isInitialized){
            match= MutableLiveData();
        }
        this.match.value=matchModel;
    }
}