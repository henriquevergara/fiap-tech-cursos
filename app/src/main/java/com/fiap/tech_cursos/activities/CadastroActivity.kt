package com.fiap.tech_cursos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.fiap.tech_cursos.R

class CadastroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val button = findViewById<Button>(R.id.button_cadastro)
        button.setOnClickListener {
            val intent= Intent(this, PainelActivity::class.java)
            startActivity(intent)
        }
    }
}