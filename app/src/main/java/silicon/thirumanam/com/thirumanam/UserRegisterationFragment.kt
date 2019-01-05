package silicon.thirumanam.com.thirumanam


import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.smartherd.globofly.models.User
import kotlinx.android.synthetic.main.fragment_user_registeration.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import silicon.thirumanam.com.thirumanam.Common.showMsg
import silicon.thirumanam.com.thirumanam.Common.WebService
import silicon.thirumanam.com.thirumanam.Common.ServiceBuilder


class UserRegisterationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_registeration, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        regBtn.setOnClickListener {
            when (TextUtils.isEmpty(idTv.text)) {
                true ->  showMsg("Enter your Id")

            }

            when (TextUtils.isEmpty(pwdTv.text)) {
                true ->  showMsg("Enter your Password")

            }
            when (TextUtils.isEmpty(sexTv.text)) {
                true ->  showMsg("Enter your Sex")

            }
            when (TextUtils.isEmpty(nameTv.text)) {
                true ->  showMsg("Enter your Name")

            }


            when ( !TextUtils.isEmpty(pwdTv.text) && !TextUtils.isEmpty(pwdTv.text) && !TextUtils.isEmpty(sexTv.text) && !TextUtils.isEmpty(nameTv.text)) {
                true ->  {

                    val newUser= User()
                    newUser.id=pwdTv.text.toString().toInt()
                    newUser.name=pwdTv.text.toString()
                    newUser.password=pwdTv.text.toString()
                    newUser.sex=pwdTv.text.toString()

                    val service: WebService = ServiceBuilder.buildService(
                        WebService::class.java)
                    val loginRequestCall: Call<User> = service.registerUser(newUser)

                    loginRequestCall.enqueue(object : Callback<User>{

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            showMsg("Could not connect to Server")
                        }

                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            if(response.isSuccessful){
                                if(response.code()==201) {
                                    val res: User = response.body()!!
                                    Toast.makeText(context, "New User has been added successfully", Toast.LENGTH_LONG).show()
                                    Log.d(LoginFragment.TAG, "" + res.toString())
                                }
                            }else {
                                val result = when (response.code()) {
                                    400 ->  "Invalid User data "+ response.code()
                                    else -> "Internal Server Error "+ response.code()
                                }
                                showMsg(result)
                            }
                        }
                    })

                }

            }
        }


    }

}
