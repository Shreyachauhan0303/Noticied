package com.example.noticied.Adapter

import android.app.Notification.CarExtender
import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.noticied.Models.Note
import com.example.noticied.R
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

class NotesAdapter(private val context: Context,val listener: NotesitemclickListener): RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {
    private val NotesList=ArrayList<Note>()
    private val fullList=ArrayList<Note>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(
            LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)

        )
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote=NotesList[position]
        holder.title.text=currentNote.title
        holder.Note_tv.text=currentNote.note
        holder.date.text=currentNote.date
        holder.date.isSelected=true
        holder.notes_layout.setCardBackgroundColor(holder.itemView.resources.getColor(randomColor(),null))
        holder.notes_layout.setOnClickListener{
            listener.onitemClicked(NotesList[holder.absoluteAdapterPosition])

        }
        holder.notes_layout.setOnLongClickListener{
            listener.onLongItemClicked(NotesList[holder.absoluteAdapterPosition],holder.notes_layout)
            true



        }


    }

    override fun getItemCount(): Int {

        return NotesList.size

    }
    fun updateList(newList:List<Note>){
        fullList.clear()
        fullList.addAll(newList)
        NotesList.clear()
        NotesList.addAll(fullList)
        notifyDataSetChanged()

    }
    fun filterList(search:String){
        NotesList.clear()
        for(item in fullList){
            if(item.title?.lowercase()?.contains(search.lowercase())==true||
                    item.note?.lowercase()?.contains(search.lowercase())==true){
                NotesList.add(item)

            }
        }
        notifyDataSetChanged()

    }


    private fun randomColor():Int{
        val list=ArrayList<Int>()
        list.add(R.color.color1)
        list.add(R.color.color2)
        list.add(R.color.color3)
        list.add(R.color.color4)
        list.add(R.color.color5)
        list.add(R.color.color6)
        list.add(R.color.color7)

        val seed=System.currentTimeMillis().toInt()
        val randomIndex= Random(seed).nextInt(list.size)
        return list[randomIndex]



    }


    inner class NoteViewHolder(itemview: View):RecyclerView.ViewHolder (itemview){
        val notes_layout=itemView.findViewById<CardView>(R.id.card_layout)
        val title=itemview.findViewById<TextView>(R.id.tv_title)
        val Note_tv=itemview.findViewById<TextView>(R.id.tv_note)
        val date=itemview.findViewById<TextView>(R.id.tv_date)
    }
    interface NotesitemclickListener{
        fun onitemClicked(note:Note)
        fun onLongItemClicked(note: Note,cardView: CardView)




    }


}