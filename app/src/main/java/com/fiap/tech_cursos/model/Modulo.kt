package com.fiap.tech_cursos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Modulo(
    @SerializedName("nome")
    val nome: String,
    @SerializedName("carga_horaria")
    val cargaHoraria: String,
    @SerializedName("conteudos")
    val conteudos: List<Conteudo>
) : Parcelable
