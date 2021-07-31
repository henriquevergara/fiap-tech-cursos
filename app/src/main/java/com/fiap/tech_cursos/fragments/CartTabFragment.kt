package com.fiap.tech_cursos.fragments


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.adapter.CartCourseCardAdapter
import com.fiap.tech_cursos.databinding.FragmentCartTabBinding
import com.fiap.tech_cursos.model.Curso
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

/**
 * The cart tab fragment that will have the list of courses and the resume info
 */
class CartTabFragment : Fragment() {
    inline fun <reified T> Gson.fromJson(json: String?) =
        fromJson<T>(json, object : TypeToken<T>() {}.type)

    private var _binding: FragmentCartTabBinding? = null
    private val binding get() = _binding!!

    /**
     * The function that will get the data to present in the screen
     */
    private fun getData() {
        val recyclerView = binding.root.findViewById<RecyclerView>(R.id.rv_carttab_courses_list)
        var prefs: SharedPreferences? = this.activity?.getSharedPreferences(
            getString(R.string.SHARED_PREFERENCES),
            Context.MODE_PRIVATE
        )
        var gson: Gson = Gson()
        var json: String? = prefs?.getString("cart_courses", null)

        val coursesInCart =
            if (!json.isNullOrEmpty()) gson.fromJson<ArrayList<Curso>>(json) else arrayListOf<Curso>()
        val viewPager2 = this.activity?.findViewById<ViewPager2>(R.id.cart_viewpager)
        recyclerView.adapter = CartCourseCardAdapter(coursesInCart.toMutableList(), viewPager2, this)

        val tvCount = binding.root.findViewById<TextView>(R.id.tv_courses_count)
        tvCount.text = getString(R.string.txt_cart_course_count, coursesInCart.count().toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentCartTabBinding.inflate(inflater, container, false)
        val view = binding.root
        getData()
        return view
    }
}