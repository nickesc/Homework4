package com.example.homework4

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView

class DreamAdapter (private val dataset: List<Dream>) : RecyclerView.Adapter<DreamAdapter.ItemViewHolder>() {

    class ItemViewHolder(private val view: View) :RecyclerView.ViewHolder(view){
        val textViewName: TextView=view.findViewById(R.id.textView_title)
        val textViewId: TextView=view.findViewById(R.id.textView_id)
        val constraintLayoutDream: ConstraintLayout = view.findViewById(R.id.constraintLayout_dream)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val adapterLayout = LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
        return ItemViewHolder(adapterLayout)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var dream:Dream = dataset[position]
        holder.textViewName.text=dream.title
        holder.textViewId.text=""+dream.id
        holder.constraintLayoutDream.setOnClickListener{
            Log.d("help",""+dream.id)
        }
    }
}

