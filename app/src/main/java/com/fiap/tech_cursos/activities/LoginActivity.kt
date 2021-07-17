package com.fiap.tech_cursos.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.fiap.tech_cursos.R
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.button_login)
        button.setOnClickListener {
            val intent= Intent(this, PainelActivity::class.java)
            startActivity(intent)
        }

        var textView_cadaste = findViewById<TextView>(R.id.textView_cadastre)
        textView_cadastre.setOnClickListener {
            val intent= Intent(this, CadastroActivity::class.java)
            startActivity(intent)
        }

    }
}