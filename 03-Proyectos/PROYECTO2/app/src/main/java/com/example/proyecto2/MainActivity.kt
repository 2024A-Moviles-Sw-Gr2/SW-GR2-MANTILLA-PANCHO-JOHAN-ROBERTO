package com.example.proyecto2

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnIniciarSesion: Button
    private lateinit var tvRegistro: TextView
    private lateinit var cbRecordarme: CheckBox
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.etEmail)
        etPassword = findViewById(R.id.etPassword)
        btnIniciarSesion = findViewById(R.id.btnIniciarSesion)
        tvRegistro = findViewById(R.id.tvRegistro)
        cbRecordarme = findViewById(R.id.cbRecordarme)

        sharedPreferences = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)

        // Cargar credenciales guardadas
        if (sharedPreferences.getBoolean("recordar", false)) {
            etEmail.setText(sharedPreferences.getString("email", ""))
            etPassword.setText(sharedPreferences.getString("password", ""))
            cbRecordarme.isChecked = true
        }

        btnIniciarSesion.setOnClickListener {
            val nombre = etEmail.text.toString()
            val contrasena = etPassword.text.toString()

            if (GestorUsuario.autenticar(nombre, contrasena)) {
                // Guardar credenciales si "Recordarme" está marcado
                if (cbRecordarme.isChecked) {
                    val editor = sharedPreferences.edit()
                    editor.putString("email", nombre)
                    editor.putString("password", contrasena)
                    editor.putBoolean("recordar", true)
                    editor.apply()
                } else {
                    // Limpiar credenciales guardadas si no está marcado
                    sharedPreferences.edit().clear().apply()
                }

                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show()
                // Navegar a ImplementosDeportivos
                val intent = Intent(this, EquipoDeportivo::class.java)
                startActivity(intent)
                finish() // Opcional: cierra MainActivity para que no se pueda volver atrás
            } else {
                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
            }
        }

        tvRegistro.setOnClickListener {
            val intent = Intent(this, RegistroLogin::class.java)
            startActivity(intent)
        }
    }
}