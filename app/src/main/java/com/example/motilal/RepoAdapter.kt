package com.example.motilal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.motilal.models.RepositoriesItem

class RepoAdapter: RecyclerView.Adapter<RepoAdapter.RepoHolder>() {
    private lateinit var list: List<RepositoriesItem>
    inner class RepoHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val txtName = itemView.findViewById<TextView>(R.id.repo_name)
        /*fun bind(){
            txtName.setOnClickListener {
                val i = Intent(itemView.context, DetailedActivity::class.java)
                i.putExtra("DATA", list.get(adapterPosition))
                itemView.context.startActivity(i)
            }
        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepoHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.repo_card, parent, false)
        return RepoHolder(v)
    }

    override fun getItemCount(): Int {
        return if (list == null){
            0
        }else{
            list.size
        }
    }

    override fun onBindViewHolder(holder: RepoHolder, position: Int) {
        val o = list.get(position)
        holder.txtName.text = o.name
        holder.txtName.setOnClickListener{
            val i = Intent(holder.itemView.context, DetailedActivity::class.java)
            i.putExtra("DATA", o)
            holder.itemView.context.startActivity(i)
        }
    }

    fun submitList(list: List<RepositoriesItem>){
        this.list = list
    }
}