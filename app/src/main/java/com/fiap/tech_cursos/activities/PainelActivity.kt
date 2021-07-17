package com.fiap.tech_cursos.activities

import retrofit2.Callback
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.`interface`.ApiInterface
import com.fiap.tech_cursos.adapter.CardViewAdapter
import com.fiap.tech_cursos.config.RetrofitConfig
import com.fiap.tech_cursos.model.CursoResponse
import retrofit2.Call
import retrofit2.Response

class PainelActivity : AppCompatActivity(R.layout.activity_painel) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getData()
    }

    fun getData(){
        val retrofitClient = RetrofitConfig
            .getRetrofitInstance("https://run.mocky.io/")

        val endpoint = retrofitClient.create(ApiInterface::class.java)
        val callback = endpoint.getCursos()

        callback.enqueue(object : Callback<CursoResponse> {

            override fun onFailure(call: Call<CursoResponse>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
                Log.i("ERROR: ","Error trying to consume API.")
            }

            override fun onResponse(call: Call<CursoResponse>, response: Response<CursoResponse>) {
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
                val cursosResponse : CursoResponse = response.body()!!
                recyclerView.adapter = CardViewAdapter(cursos = cursosResponse.cursos)
            }
        })
    }
}