package com.fiap.tech_cursos.model


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Curso (
    @SerializedName("id")
    val id: Long,
    @SerializedName("nivel")
    val nivel: String,
    @SerializedName("nome")
    val nome: String,
    @SerializedName("descricao")
    val descricao: String,
    @SerializedName("preco_original")
    val preco: String,
    @SerializedName("preco_promocional")
    val precoPromocional: String,
    @SerializedName("url_imagem")
    val imagem: String,
    @SerializedName("porc_desconto")
    val porcDesconto: Int,
    @SerializedName("modulos")
    val modulos: List<Modulo>
) : Parcelable