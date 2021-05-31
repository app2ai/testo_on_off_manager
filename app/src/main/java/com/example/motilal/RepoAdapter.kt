package com.example.motilal

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
    }

    fun submitList(list: List<RepositoriesItem>){
        this.list = list
    }
}