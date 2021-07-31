package com.fiap.tech_cursos.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.model.Curso

class CardViewHolder(cursoView: View) : RecyclerView.ViewHolder(cursoView) {

    fun bind(curso: Curso) {
        val title = itemView.findViewById<TextView>(R.id.title)
        title.text = curso.nome
        val description = itemView.findViewById<TextView>(R.id.description)
        description.text = curso.descricao
        val image = itemView.findViewById<ImageView>(R.id.image)
        Glide.with(itemView.context).load(curso.imagem).error(R.drawable.ic_launcher_background).into(image)
    }
}