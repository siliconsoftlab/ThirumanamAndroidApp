package silicon.thirumanam.com.thirumanam


import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btm_navigation_view.setupWithNavController(
            Navigation.findNavController(
                activity as Activity,
                R.id.nav_host_fragment_btm_nav
            )
        )
        // btm_navigation_view.setOnNavigationItemSelectedListener { item ->  onNavDestinationSelected(item,Navigation.findNavController(activity as Activity,R.id.nav_host_fragment_btm_nav))}

    }
}
