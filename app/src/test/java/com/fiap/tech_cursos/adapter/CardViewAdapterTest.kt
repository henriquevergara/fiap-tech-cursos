package com.fiap.tech_cursos.adapter

import com.fiap.tech_cursos.model.Curso
import org.junit.*


class CardViewAdapterTest {

    lateinit var cursoJava : Curso
    lateinit var cursoAndroid : Curso

    @Before
    fun setup() {
        cursoJava = Curso(
            1L,
            "Iniciante",
            "Java Básico",
            "Aprenda Java",
            "20.00",
            "10.00",
            "url",
            50,
            emptyList()
        )

        cursoAndroid = Curso(
            2L,
            "Iniciante",
            "Android Básico",
            "Aprenda Android",
            "20.00",
            "10.00",
            "url",
            50,
            emptyList()
        )
    }

    @Test
    fun shouldReturnItensCount() {
        val cursos = listOf(cursoJava, cursoAndroid)
        var adapter = CardViewAdapter(cursos)

        adapter.itemCount

        Assert.assertEquals(2, adapter.itemCount)
    }

    @Test
    fun shouldReturnFalseWhenItensCount() {
        val cursos = listOf(cursoJava, cursoAndroid)
        var adapter = CardViewAdapter(cursos)

        adapter.itemCount

        Assert.assertNotEquals(3, adapter.itemCount)
    }
}