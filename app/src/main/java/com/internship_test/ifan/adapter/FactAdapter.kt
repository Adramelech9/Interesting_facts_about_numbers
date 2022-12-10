package com.internship_test.ifan.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.internship_test.ifan.FactActivity
import com.internship_test.ifan.R

class FactAdapter(private val listener: Listener, private val listFact: ArrayList<String>) :
    RecyclerView.Adapter<FactAdapter.FactViewHolder>() {
    class FactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var fact: TextView = itemView.findViewById(R.id.fact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_fact, parent, false)
        return FactViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        holder.fact.text = listFact[position]
        holder.fact.setOnClickListener {
            FactActivity.FACT = listFact[position]
            listener.start()

        }
    }

    interface Listener {
        fun start()
    }

    override fun getItemCount() = 10
}