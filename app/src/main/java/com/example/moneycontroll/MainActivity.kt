package com.example.moneycontroll

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var notes : ArrayList<Note> = ArrayList()
    lateinit var noteAdapter: ListBaseAdapter
    lateinit var notesListView : ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val SampleNote : Note = Note("Sample Title", "Sample Disc")
        notes.add(SampleNote)
        noteAdapter = ListBaseAdapter(this,notes)

        notesListView = findViewById(R.id.list)
        notesListView.adapter = noteAdapter
    }
}