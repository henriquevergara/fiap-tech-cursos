package com.fiap.tech_cursos.`interface`

import com.fiap.tech_cursos.model.CursoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("cursos")
    fun getCursos() : Call<CursoResponse>
}