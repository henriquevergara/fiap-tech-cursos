package com.fiap.tech_cursos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.adapter.ModulosExpandableListAdapter
import com.fiap.tech_cursos.model.Conteudo
import com.fiap.tech_cursos.model.Curso
import com.fiap.tech_cursos.model.Modulo
import org.w3c.dom.Text

class CursoDetalhesActivity : AppCompatActivity() {

    internal var expandableListView: ExpandableListView? = null
    internal var adapter: ExpandableListAdapter? = null
    internal var titleList: List<String> ? = null

    val data: HashMap<String, List<String>>
        get() {
            val listData = HashMap<String, List<String>>()

            val curso : Curso = intent.getParcelableExtra<Curso>("detalhes_curso")!!

            for (modulo: Modulo in curso.modulos) {
                val conteudos = ArrayList<String>()

                for (conteudo: Conteudo in modulo.conteudos){
                    conteudos.add(conteudo.descricao)
                }

                listData[modulo.nome + " - " + modulo.cargaHoraria] = conteudos
            }

            return listData
        }

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

        Glide.with(this).load(curso.imagem).error(R.drawable.ic_launcher_background).into(imagemCurso)
        nivel.text = curso.nivel
        descricao.text = curso.descricao

        expandableListView = findViewById(R.id.expandableListView)

        if (expandableListView != null) {
            val listData = data
            titleList = ArrayList(listData.keys)
            adapter = ModulosExpandableListAdapter(this, titleList as ArrayList<String>, listData)
            expandableListView!!.setAdapter(adapter)
        }
    }
}