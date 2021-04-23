package com.example.homework4

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class DreamListAdapter() : ListAdapter<Dream, DreamListAdapter.DreamViewHolder> (DreamComparator()){
    class DreamViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val textViewTitle: TextView =itemView.findViewById(R.id.textView_title)
        val textViewId: TextView =itemView.findViewById(R.id.textView_id)
        val constraintLayoutDream: ConstraintLayout = itemView.findViewById(R.id.constraintLayout_dream)

        fun bindText(text:String?, textView: TextView){
            textView.text=text
        }
        fun bindText(text:Int?, textView: TextView){
            textView.text=""+text
        }

        fun bindClick(dream:Int, constraintLayout: ConstraintLayout){
            constraintLayout.setOnClickListener{
                Log.d("help", "" + dream)
            }
        }


        companion object{
            fun create (parent:ViewGroup) : DreamViewHolder{

                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_dream, parent, false)
                return DreamViewHolder(view)
            }
        }
    }
    class DreamComparator : DiffUtil.ItemCallback<Dream>(){
        override fun areItemsTheSame(oldItem: Dream, newItem: Dream): Boolean {

            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Dream, newItem: Dream): Boolean {
            return oldItem.id==newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamViewHolder {
        return DreamViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: DreamViewHolder, position: Int) {
        val currentDream=getItem(position)
        holder.bindText(currentDream.title, holder.textViewTitle)
        holder.bindText(currentDream.id, holder.textViewId)
        holder.bindClick(currentDream.id, holder.constraintLayoutDream)

    }
}