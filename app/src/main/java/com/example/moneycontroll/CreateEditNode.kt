package com.example.moneycontroll

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.gson.Gson
import kotlin.collections.ArrayList

public class CreateEditNode: AppCompatActivity(), View.OnClickListener{

    lateinit var titleEdit : EditText
    lateinit var discEdit : EditText

    lateinit var btnSave : Button
    lateinit var btnRemove : Button

    lateinit var notes : ArrayList<Note>
    lateinit var note : Note
    var noteIndex : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.create_edit_note)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.create_edit_node)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        notes = loadArray() ?: ArrayList()
        note = intent.getParcelableExtra<Note>("NOTE_DATA") as Note
        noteIndex = intent.getIntExtra("NOTE_INDEX",-1)
        titleEdit = findViewById(R.id.et_edit_title)
        discEdit = findViewById(R.id.et_edit_disc)

        btnSave = findViewById(R.id.btn_Save)
        btnRemove = findViewById(R.id.btn_Delete)

        btnSave.setOnClickListener(this)
        btnRemove.setOnClickListener(this)

        val myIntent = Intent(this, CreateEditNode::class.java)
        //Post initialization

        titleEdit.setText(note.getTitle())
        discEdit.setText(note.getDesc())


    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn_Save -> Save()
            R.id.btn_Delete -> Delete()
        }
    }

    override fun onBackPressed() {
        finish()
        @Suppress("DEPRECATION")
        super.onBackPressed()
    }
    fun Exit(){

        finish()
    }
    fun Save(){

        note.ChangeTitle(titleEdit.text?.toString())
        note.ChangeDiscription(discEdit.text?.toString())

        notes[noteIndex] = note
        saveArray()
        Exit()
    }
    fun Delete(){
        notes.removeAt(noteIndex)
        saveArray()
        Exit()
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
    fun saveArray(){
        val sharedPreferences: SharedPreferences = this.getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val jsonArray = Gson().toJson(notes)

        editor.putString("notes", jsonArray)
        editor.apply()
    }

}