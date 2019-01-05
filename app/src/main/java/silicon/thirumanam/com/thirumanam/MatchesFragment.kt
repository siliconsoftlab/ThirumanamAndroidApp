package silicon.thirumanam.com.thirumanam


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.smartherd.globofly.models.User
import kotlinx.android.synthetic.main.fragment_matches.*
import silicon.thirumanam.com.thirumanam.Common.Logd
import silicon.thirumanam.com.thirumanam.Common.showMsg


class MatchesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager= LinearLayoutManager(activity)
        layoutManager.orientation= LinearLayout.VERTICAL
        matchList.layoutManager=layoutManager

        val viewModel= ViewModelProviders.of(this).get(MatchesViewModel::class.java)

        viewModel.getMatches().observe(this, Observer<List<User>> {  users->
            showMsg("size of the match is ${users?.size}")
             Logd(this::class.java.simpleName,"size of the match is ${users?.size}")
            val adapter= MatchesAdapter(
                context,
                users!!,this
            )
            matchList.adapter=adapter
        })







    }

}
