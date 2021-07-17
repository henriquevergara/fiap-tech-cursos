package com.fiap.tech_cursos.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Conteudo(
    @SerializedName("descricao")
    val descricao: String
) : Parcelable