package silicon.thirumanam.com.thirumanam.User


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartherd.globofly.models.User
import kotlinx.android.synthetic.main.fragment_user_profile.*
import silicon.thirumanam.com.thirumanam.R


class UserProfileFragment : Fragment() {
//private lateinit var viewModel: UserProfileViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

       val viewModel=ViewModelProviders.of(this).get(UserProfileViewModel::class.java)
        viewModel.getUser("10","India").observe(this, Observer<User> {  user->
            Log.d("******* 2","observe "+user.toString())
            username.text=user?.name
        })
    }
}
