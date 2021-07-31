package com.fiap.tech_cursos.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.adapter.ModulosExpandableListAdapter
import com.fiap.tech_cursos.model.Curso
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * Course Details Activity
 */
class CursoDetalhesActivity : AppCompatActivity() {
    private lateinit var curso: Curso
    private lateinit var coursesInCart: ArrayList<Curso>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_curso_detalhes)
        loadCursos()
    }

    inline fun <reified T> Gson.fromJson(json: String?) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    private fun loadCursos() {
        curso = intent.getParcelableExtra<Curso>("detalhes_curso")!!
        val imagemCurso = findViewById<ImageView>(R.id.imagem_curso)
        val nome = findViewById<TextView>(R.id.nomeCurso)
        val nivel = findViewById<TextView>(R.id.nivelCurso)
        val descricao = findViewById<TextView>(R.id.descricaoCurso)

        Glide.with(this).load(curso.imagem).error(R.drawable.ic_launcher_background)
            .into(imagemCurso)
        nome.text = curso.nome
        nivel.text = curso.nivel
        descricao.text = curso.descricao

        val expandableListView = findViewById<ExpandableListView>(R.id.expandableListView)
        var adapter = ModulosExpandableListAdapter(this, curso.modulos)
        expandableListView!!.setAdapter(adapter)
    }

    override fun onResume() {
        super.onResume()
        loadAddButton()
    }

    /**
     * Loads the Add to Cart button, checking if it should be visible or not (course already in cart)
     */
    private fun loadAddButton() {
        var prefs: SharedPreferences = getSharedPreferences(getString(R.string.SHARED_PREFERENCES), Context.MODE_PRIVATE)
        var gson: Gson = Gson()
        var json: String? = prefs.getString("cart_courses", null)

        coursesInCart = if (!json.isNullOrEmpty()) gson.fromJson<ArrayList<Curso>>(json) else arrayListOf<Curso>()

        val btnAdd = findViewById<Button>(R.id.button_carrinho)
        val tvCourseInCart = findViewById<TextView>(R.id.tv_course_in_cart)

        val isCourseInCart = coursesInCart.find { it.id == curso.id } != null
        if (isCourseInCart) {
            btnAdd.visibility = Button.GONE
            tvCourseInCart.visibility = TextView.VISIBLE
        } else {
            btnAdd.visibility = Button.VISIBLE
            tvCourseInCart.visibility = TextView.GONE
        }

        btnAdd.setOnClickListener(this::addCourseToCart)
    }

    /**
     * The Add button on click event, to add the course to cart and navigate to cart
     */
    private fun addCourseToCart(v: View) {
        coursesInCart.add(curso)

        val prefs: SharedPreferences =
            getSharedPreferences(getString(R.string.SHARED_PREFERENCES), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson: Gson = Gson()
        val json: String = gson.toJson(coursesInCart)
        editor.putString(getString(R.string.cart_courses), json)
        editor.apply()

        val intent = Intent(this, CarrinhoActivity::class.java)
        startActivity(intent)

    }
}