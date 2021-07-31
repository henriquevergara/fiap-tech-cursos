package com.fiap.tech_cursos.adapter

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.activities.CursoDetalhesActivity
import com.fiap.tech_cursos.model.Curso

class CardViewAdapter(
    private val cursos: List<Curso>,
) : RecyclerView.Adapter<CardViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_adapter_layout,parent,false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.bind(cursos[position])
        holder.itemView.setOnClickListener {
            val intent= Intent(holder.itemView.context, CursoDetalhesActivity::class.java)
            intent.putExtra("detalhes_curso", cursos[position])
            context.startActivity(intent)
            Log.i("ERROR: ","Erro ao realizar o bind. " + cursos[position].nome)
        }
    }

    override fun getItemCount(): Int {
        return cursos.size
    }
}