package com.fiap.tech_cursos.model

import com.google.gson.annotations.SerializedName

class CursoResponse(
    @SerializedName("cursos")
    var cursos: List<Curso>
)
