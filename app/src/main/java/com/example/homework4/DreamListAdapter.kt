package com.example.homework4

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Job

class DreamListAdapter(theContext: Context) : ListAdapter<Dream, DreamListAdapter.DreamViewHolder> (DreamComparator()){
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

        fun bindClick(context: Context, dream:Dream, constraintLayout: ConstraintLayout){
            constraintLayout.setOnClickListener{
                Log.d("help", "" + dream.emotion+ " " + dream.content + " " + dream.reflection)
                var intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("id", dream.id)
                context.startActivity(intent)
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
        holder.bindClick(holder.itemView.context, currentDream, holder.constraintLayoutDream)

    }
}