package silicon.thirumanam.com.thirumanam


import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.smartherd.globofly.models.Interest
import com.smartherd.globofly.models.User
import kotlinx.android.synthetic.main.fragment_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import silicon.thirumanam.com.thirumanam.Common.showMsg
import silicon.thirumanam.com.thirumanam.Common.WebService
import silicon.thirumanam.com.thirumanam.Common.ServiceBuilder
import android.arch.lifecycle.Observer
import silicon.thirumanam.com.thirumanam.User.UserProfileViewModel

class LoginFragment : Fragment() {
    companion object {
        val TAG:String=LoginFragment::class.java.simpleName;
        var preferences: SharedPreferences? =null

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferences = activity!!.getPreferences(Context.MODE_PRIVATE)?:return

        rememberCkbx.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked){
                showMsg(" Is checked")
                saveData()
            }else{
                showMsg(" Not checked")
            }
        }


        lgn_btn.setOnClickListener {  //it.findNavController().navigate(R.id.action_loginFragment_to_userProfileFragment)

            when (TextUtils.isEmpty(idTv.text)) {
                true ->  showMsg("Enter your Id")
            }

            when (TextUtils.isEmpty(pwdTv.text)) {
                true ->  showMsg("Enter your Password")
            }

            when ( !TextUtils.isEmpty(pwdTv.text) && !TextUtils.isEmpty(pwdTv.text)) {
                true ->  {
                    val filterLogin= HashMap<String,String>()
                    filterLogin["id"]=idTv.text.toString()
                    filterLogin["password"]=pwdTv.text.toString()
                    //showMsg(idTv.text.toString()+" "+pwdTv.text.toString())
                    val viewModel= ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
                    viewModel.getUser(idTv.text.toString(),pwdTv.text.toString()).observe(this, Observer<User> {  user->
                        //username.text=user?.name
                        showMsg(""+user?.name)
                        it.findNavController().navigate(R.id.toHomeFragment)
                    })

                }

            }
        }

        registerTv.setOnClickListener {
            it.findNavController().navigate(R.id.toUserRegisterationFragment) }
    }

    fun saveData(){

        with(preferences!!.edit()){
            putString("Id",idTv?.text.toString())
            putString("Password",pwdTv?.text.toString())
            commit()
        }
    }


    fun getData(){

        val str_name = preferences?.getString("Id", "")
        val int_number = preferences?.getInt("Password", 0)

    }
    fun setup(){



        //Getting All Matches
        val filterMatches= HashMap<String,String>()
        filterMatches["sex"]="Male"

        //update user
        val targetUser= User()
        targetUser.id=13
        targetUser.name="Selvi"
        targetUser.password="1234"
        targetUser.incomingInterest= listOf(Interest(1,"Pending"),Interest(2,"Rejected"))
        targetUser.outgoingInterest= listOf(Interest(1,"Pending"),Interest(2,"Rejected"))

        //Getting one Match || deleting user
        val filterMatch= HashMap<String,String>()
        filterMatch["id"]="13"

        // register(newUser)
        //login(filterLogin)
        //getMatches(filterMatches);
        //updateUser(targetUser);
        //getUser(filterMatch);
        // deleteUser(filterMatch);


    }




    private fun getMatches(filterMatches:HashMap<String,String>){
        val service: WebService = ServiceBuilder.buildService(WebService::class.java)


        val loginRequestCall: Call<List<User>> = service.getMatches(filterMatches)
        loginRequestCall.enqueue(object : Callback<List<User>>{
            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                Toast.makeText(context,"Could not get data",Toast.LENGTH_LONG).show()
                displayError(t)
            }

            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                if(response.isSuccessful){
                    val res:List<User> = response.body()!!
                    Toast.makeText(context,"Retrieved all Matches",Toast.LENGTH_LONG).show()
                    Log.d(TAG,""+res.toString())

                }else {
                    val result = when (response.code()) {
                        404 ->  "Users not found for your query "+ response.code()
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()
                    }
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun updateUser(targetUser : User){
        val service: WebService = ServiceBuilder.buildService(WebService::class.java)

        val loginRequestCall: Call<User> = service.updateUser(targetUser)
        loginRequestCall.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                displayError(t)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    val res:User = response.body()!!
                    Toast.makeText(context,"Updated user",Toast.LENGTH_LONG).show()
                    Log.d(TAG,""+res.toString())

                }else{
                    val result = when (response.code()) {
                        404 ->  "User Not Found "+ response.code()
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()

                    }
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun getUser(filter:HashMap<String,String>){
        val service: WebService = ServiceBuilder.buildService(WebService::class.java)
        val loginRequestCall: Call<User> = service.getUser(filter)
        loginRequestCall.enqueue(object : Callback<User>{
            override fun onFailure(call: Call<User>, t: Throwable) {
                displayError(t)
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                if(response.isSuccessful){
                    val res:User = response.body()!!
                    Toast.makeText(context,"User details retrieved",Toast.LENGTH_LONG).show()
                    Log.d(TAG,""+res.toString())

                }else{
                    val result = when (response.code()) {
                        404 ->  "User Not Found "+ response.code()
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()

                    }
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun deleteUser(filterMatches:HashMap<String,String>){
        val service: WebService = ServiceBuilder.buildService(WebService::class.java)


        val loginRequestCall: Call<Unit> = service.deleteUser(filterMatches)
        loginRequestCall.enqueue(object : Callback<Unit>{
            override fun onFailure(call: Call<Unit>, t: Throwable) {
                Toast.makeText(context,"Could not get data",Toast.LENGTH_LONG).show()
                displayError(t)
            }

            override fun onResponse(call: Call<Unit>, response: Response<Unit>) {
                if(response.isSuccessful){
                    Toast.makeText(context,"Deleted user",Toast.LENGTH_LONG).show()
                }else{
                    val result = when (response.code()) {
                        404 ->  "User Not Found "+ response.code()
                        500 -> "Internal Server Error "+ response.code()
                        else -> "Internal Server Error "+ response.code()

                    }
                    Toast.makeText(context, result, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun displayError(t: Throwable){
        Toast.makeText(context,"Could not connect to Server" ,Toast.LENGTH_LONG).show()
        Log.d(TAG, "onFailure t.localizedMessage "+t.localizedMessage )
        Log.d(TAG, "onFailure t.message"+t.message )
        Log.d(TAG, "onFailure t.stackTrace"+t.stackTrace )
        Log.d(TAG, "onFailure t.cause" +t.cause )
        Log.d(TAG, "onFailure t.printStackTrace()"+t.printStackTrace() )

    }
}
