package silicon.thirumanam.com.thirumanam

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.smartherd.globofly.models.User
import kotlinx.android.synthetic.main.match_single_item.view.*


class MatchesAdapter(val context: Context?,val matches:List<User>,val fragment: Fragment ): RecyclerView.Adapter<MatchesAdapter.MatchesViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
     val view=  LayoutInflater.from(context).inflate(R.layout.match_single_item,parent,false)
        return MatchesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return matches.size

    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        val match=matches[position]
        holder.setData(match,position)
         }

    inner class MatchesViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        var  curmatchModel: User?=null
        var position: Int?=0
        init {
            itemView.setOnClickListener {
                val matches= ViewModelProviders.of(fragment).get(MatchViewModel::class.java)
                matches.setMatch(curmatchModel!!);

            }


        }
        fun setData(match: User?, position: Int) {
            itemView.nameTv.text=match!!.name
            itemView.ageTv.text=match!!.sex
            itemView.eduTv.text=match!!.password
            this.curmatchModel=match
            this.position=position
        }
    }
}