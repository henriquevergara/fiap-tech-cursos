package com.fiap.tech_cursos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.adapter.ModulosExpandableListAdapter
import com.fiap.tech_cursos.model.Curso

class CursoDetalhesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_detalhes)
        loadCursos()
    }

    fun loadCursos() {
        val curso : Curso = intent.getParcelableExtra<Curso>("detalhes_curso")!!
        val imagemCurso = findViewById<ImageView>(R.id.imagem_curso)
        val nome = findViewById<TextView>(R.id.nomeCurso)
        val nivel = findViewById<TextView>(R.id.nivelCurso)
        val descricao = findViewById<TextView>(R.id.descricaoCurso)

        Glide.with(this).load(curso.imagem).error(R.drawable.ic_launcher_background).into(imagemCurso)
        nome.text = curso.nome
        nivel.text = curso.nivel
        descricao.text = curso.descricao

        val expandableListView = findViewById<ExpandableListView>(R.id.expandableListView)
        var adapter = ModulosExpandableListAdapter(this, curso.modulos)
        expandableListView!!.setAdapter(adapter)
    }
}