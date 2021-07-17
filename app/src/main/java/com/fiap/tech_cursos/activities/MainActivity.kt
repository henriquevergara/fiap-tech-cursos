package com.fiap.tech_cursos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.model.Curso
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_detalhes)

        loadCursos()
    }


    fun loadCursos() {

        val curso : Curso = intent.getParcelableExtra<Curso>("detalhes_curso")!!
        val imagemCurso = findViewById<ImageView>(R.id.imagem_curso)
        val nivel = findViewById<TextView>(R.id.nivel)
        val descricao = findViewById<TextView>(R.id.curso_descricao)
        val modulos = findViewById<TextView>(R.id.modulos)

        Glide.with(this).load(curso.imagem).error(R.drawable.ic_launcher_background).into(imagemCurso)
        nivel.text = curso.nivel
        descricao.text = curso.descricao
        modulos.text = curso.modulos.toString()
    }
}