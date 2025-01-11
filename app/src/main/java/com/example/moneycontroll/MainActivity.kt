package com.example.moneycontroll

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var noteAdapter: ListBaseAdapter
    lateinit var notesListView : ListView
    lateinit var btnAdd : Button

    var notes : ArrayList<Note> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notes = loadArray() ?: ArrayList<Note>()
        noteAdapter = ListBaseAdapter(this,notes)

        notesListView = findViewById(R.id.list)
        btnAdd = findViewById(R.id.btn_add)

        notesListView.adapter = noteAdapter
        btnAdd.setOnClickListener(this)

        notesListView.setOnItemClickListener{ parent, view, positon, id ->
            val ClickedItem = parent.getItemAtPosition(positon) as Note
            openNote(ClickedItem)
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_add ->{
                var newNote = Note("Title","")
                notes.add(newNote)
                Log.d("create Note", "Created and added")
                openNote(newNote)
            }
        }
    }
    fun saveArray(){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val jsonArray = Gson().toJson(notes)

        editor.putString("notes", jsonArray)
        editor.apply()
    }
    fun loadArray() : ArrayList<Note>?{
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val jsonArray = sharedPreferences.getString("notes", null)

        return if (jsonArray != null) {
            val noteType = object : com.google.gson.reflect.TypeToken<ArrayList<Note>>() {}.type
            Gson().fromJson(jsonArray, noteType)
        } else {
            null
        }
    }
    fun openNote(note : Note){
        val myIntent = Intent(this, CreateEditNode::class.java)
        saveArray()
        myIntent.putExtra("NOTE_DATA",note)
        myIntent.putExtra("NOTE_INDEX",notes.indexOf(note))
        startActivity(myIntent)
    }
    override fun onResume() {
        super.onResume()
        val updatedNotes = loadArray() ?: ArrayList<Note>()
        notes.clear()
        notes.addAll(updatedNotes)

        // Notify the adapter about the data changes
        noteAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()
        saveArray()
    }
}