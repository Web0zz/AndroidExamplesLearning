package com.example.hashgeneratorapp.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hashgeneratorapp.R
import com.example.hashgeneratorapp.model.Hash
import com.example.hashgeneratorapp.viewmodel.HashViewModel


class ListAdapter(
    var clickListener: ClickListener
): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var hashList = emptyList<Hash>()


    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val hash_tag_text: TextView = itemView.findViewById(R.id.hash_tag_list_view)
        val hash_text: TextView = itemView.findViewById(R.id.hash_list_view)
    }

    interface ClickListener {
        fun ClickedItem(id: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hash_list_section, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return hashList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = hashList[position]
        holder.hash_tag_text.text = currentItem.hashTag
        holder.hash_text.text = currentItem.hash

        holder.itemView.setOnClickListener {
            clickListener.ClickedItem(currentItem.hashId)
        }
    }

    fun setData(hash: List<Hash>) {
        this.hashList = hash
        notifyDataSetChanged()
    }
}