package com.example.moneycontroll

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.ArrayList


public class ListBaseAdapter(ncontext: Context, nlist : ArrayList<Note>) : BaseAdapter() {

    private var context : Context = ncontext
    private var list : ArrayList<Note> = nlist

    override fun getCount(): Int {
        return list.size
    }
    override fun getItem(pos : Int): Note{
        return list[pos]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, nconvertView: View?, parent: ViewGroup): View {
        val convertView = nconvertView ?: LayoutInflater.from(context).inflate(R.layout.list_item,parent,false)

        var curr: Note = getItem(position)

        var textViewNoteTitle : TextView = convertView.findViewById(R.id.list_item_title)
        var textViewNoteDate : TextView = convertView.findViewById(R.id.list_item_date)

        textViewNoteTitle.text = curr.getTitle()
        textViewNoteDate.text =curr.getDate()

        return convertView
    }
}