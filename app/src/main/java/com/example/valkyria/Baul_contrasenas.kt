package com.example.valkyria

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Baul_contrasenas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_baul_contrasenas)

        val recycler = findViewById<RecyclerView>(R.id.lista_contrasenas)

        recycler.layoutManager = LinearLayoutManager(this)

// Datos de prueba
        val lista = listOf(
            "Gmail - alex@gmail.com",
            "Netflix - cuenta123",
            "GitHub - dev_user",
            "Spotify - playlist99"
        )

        recycler.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
                return object : RecyclerView.ViewHolder(view) {}
            }

            override fun getItemCount() = lista.size

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val text = holder.itemView.findViewById<TextView>(android.R.id.text1)
                text.text = lista[position]
                text.setTextColor(Color.WHITE)
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}