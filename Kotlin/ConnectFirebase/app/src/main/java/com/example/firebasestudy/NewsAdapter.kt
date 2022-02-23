package com.example.firebasestudy

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class NewsAdapter(private val dataset:ArrayList<User>): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {
    class NewsViewHolder(val view : View) : RecyclerView.ViewHolder(view){
        val button: Button = view.findViewById(R.id.button_item)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return NewsViewHolder(layout)

    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val item = dataset[position]
        holder.button.text = item.username
    }


    override fun getItemCount(): Int {
        return dataset.size
    }
}