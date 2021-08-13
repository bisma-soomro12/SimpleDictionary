package com.example.simpledictionary

import adapters.WordAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simpledictionary.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val binding= ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //val letterId = intent?.extras?.getString("letter").toString()
        val letterId = intent?.extras?.getString(LETTER).toString()
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = WordAdapter(letterId, this)
    }
    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX= "https://www.google.com/search?q="


    }

}