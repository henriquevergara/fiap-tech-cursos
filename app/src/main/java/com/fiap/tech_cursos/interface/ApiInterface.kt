package com.fiap.tech_cursos.`interface`

import com.fiap.tech_cursos.model.CursoResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("v3/780f129e-1774-4cb4-b202-a558746a6814")
    fun getCursos() : Call<CursoResponse>
}