package com.example.valkyria

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.widget.addTextChangedListener
import java.util.Calendar
import androidx.activity.OnBackPressedCallback

class Baul_contrasenas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_baul_contrasenas)

        val recycler = findViewById<RecyclerView>(R.id.lista_contrasenas)

        recycler.layoutManager = LinearLayoutManager(this)

        val lista = listOf(
            PasswordItem("Gmail", "alex.walker@gmail.com", R.drawable.ic_gmail),
            PasswordItem("Netflix", "family_account_premium", R.drawable.ic_netflix),
            PasswordItem("GitHub", "dev_walker_2024", R.drawable.ic_github),
            PasswordItem("Adobe", "design.studio@work.com", R.drawable.ic_adobe),
            PasswordItem("Spotify", "playlist_master_99", R.drawable.ic_spotify)
        )

        val adapter = PasswordAdapter(lista) { item ->

            val intent = Intent(this, DetallesContrasena::class.java)

            intent.putExtra("nombre", item.nombre)
            intent.putExtra("usuario", item.usuario)
            intent.putExtra("icono", item.icono)

            startActivity(intent)
        }

        recycler.adapter = adapter
        val buscador = findViewById<EditText>(R.id.buscador)

        buscador.addTextChangedListener {

            val texto = it.toString().trim()
            adapter.filtrar(texto)
        }

        val saludoTxt = findViewById<TextView>(R.id.txt_saludo)

        val prefs = getSharedPreferences("usuarios", MODE_PRIVATE)
        val nombre = prefs.getString("nombre_usuario", "Usuario")

        val hora = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)

        val saludo = when {
            hora in 6..11 -> "Buenos días"
            hora in 12..18 -> "Buenas tardes"
            else -> "Buenas noches"
        }

        saludoTxt.text = "$saludo, $nombre"

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finishAffinity()
            }
        })
    }
}