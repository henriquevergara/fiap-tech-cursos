package com.fiap.tech_cursos.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.model.Modulo

class ModulosExpandableListAdapter internal constructor(private val context: Context, private val modulos: List<Modulo>) : BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return this.modulos.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return this.modulos[groupPosition].conteudos!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return this.modulos[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return this.modulos[groupPosition].conteudos.get(childPosition)
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val modulo = modulos.get(groupPosition)
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_group_modulos, null)
        }
        val tituloModulo = convertView!!.findViewById<TextView>(R.id.tituloModulo)
        tituloModulo.setTypeface(null, Typeface.BOLD)
        tituloModulo.text = modulo.nome + " - " + modulo.cargaHoraria
        return convertView
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        var convertView = convertView
        val conteudo = modulos.get(groupPosition).conteudos.get(childPosition)
        if (convertView == null) {
            val layoutInflater = this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView = layoutInflater.inflate(R.layout.list_curso_modulo, null)
        }
        val tituloConteudo = convertView!!.findViewById<TextView>(R.id.conteudoListItem)
        tituloConteudo.text = conteudo.descricao
        return convertView
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}