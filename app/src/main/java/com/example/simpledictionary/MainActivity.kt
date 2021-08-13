package com.example.simpledictionary

import adapters.LetterAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.simpledictionary.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private var isLinearLayoutManager = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerView
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = LetterAdapter()

       // Toast.makeText(this, "I am here", Toast.LENGTH_SHORT).show()
        chooseLayout()
        //Toast.makeText(this, "I am here yoo", Toast.LENGTH_SHORT).show()


    }

    private fun chooseLayout() {
        if (isLinearLayoutManager) {
            recyclerView.layoutManager = LinearLayoutManager(this)
        } else {
            recyclerView.layoutManager = GridLayoutManager(this, 3)
        }
        recyclerView.adapter = LetterAdapter()

    }

    private fun setIcon(menuItem: MenuItem) {
        if (menuItem == null)
            return

        menuItem.icon =
            if (isLinearLayoutManager)
                ContextCompat.getDrawable(this, R.drawable.ic_linear)
            else
                ContextCompat.getDrawable(this, R.drawable.ic_grid)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.layout_menu, menu)

        // creating varible for the menu
        val layoutButton = menu?.findItem(R.id.switch_layout)
        setIcon(layoutButton!!)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.switch_layout -> {
                isLinearLayoutManager = !isLinearLayoutManager
                chooseLayout()
                setIcon(item)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }


    }
}