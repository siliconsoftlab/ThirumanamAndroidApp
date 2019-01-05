package silicon.thirumanam.com.thirumanam

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

/**
 * Created by Govind on 3/20/2018.
 */
class TabPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragments = ArrayList<Fragment>()
    private val titles = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Matches_All_Fragment()
            }
            1 -> Matches_Filter_Fragment()
            else -> {
                return Matches_All_Fragment()
            }
        }
    }

    override fun getCount(): Int = fragments.size

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "First Tab"
            1 -> "Second Tab"
            else -> {
                return "Third Tab"
            }
        }
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }
}