package com.example.notes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ItemNotesBinding
import com.example.notesaddingapp.room.Notes


class MyAdapter(noteList1: Context, val noteList: List<Notes>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){
    class MyViewHolder(val binding:ItemNotesBinding):RecyclerView.ViewHolder(binding.root){

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemNotesBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int =noteList.size
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data=noteList[position]
        holder.binding.apply {
            tvtitle.text=data.title
            tvcontent.text=data.content
            tvdate.text=data.date
        }
        holder.binding.root.setOnClickListener {
            val action=Home_fragmentDirections.actionHomeFragmentToUpateFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }
}