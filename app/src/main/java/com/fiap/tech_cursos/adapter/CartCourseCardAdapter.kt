package com.fiap.tech_cursos.adapter

import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.fragments.CartTabFragment
import com.fiap.tech_cursos.model.Curso
import com.google.gson.Gson

/**
 *  Cart courses list RecyclerView adapter class
 */
class CartCourseCardAdapter(
    private val coursesInCart: MutableList<Curso>,
    private val viewPager2: ViewPager2?,
    private val cartTabFragment: CartTabFragment
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * The RecyclerView ViewHolder class for the courses card view
     */
    inner class CartCourseCardViewHolder(courseView: View) : RecyclerView.ViewHolder(courseView) {
        /**
         * The binding function for the courses card view
         */
        fun bindView() {
            val course = coursesInCart[adapterPosition]
            val courseImage = itemView.findViewById<ImageView>(R.id.iv_cart_course_image)
            val courseTitle = itemView.findViewById<TextView>(R.id.tv_course_title)
            val courseDesc = itemView.findViewById<TextView>(R.id.tv_course_desc)
            val fromPrice = itemView.findViewById<TextView>(R.id.tv_from_price)
            val finalPrice = itemView.findViewById<TextView>(R.id.tv_final_price)
            val removeBtn = itemView.findViewById<Button>(R.id.btn_remove)
            removeBtn.setOnClickListener {
                coursesInCart.removeAt(adapterPosition)

                val prefs: SharedPreferences =
                    itemView.context.getSharedPreferences(itemView.context.getString(R.string.SHARED_PREFERENCES), Context.MODE_PRIVATE)
                val editor: SharedPreferences.Editor = prefs.edit()
                val gson: Gson = Gson()
                val json: String = gson.toJson(coursesInCart)
                editor.putString(itemView.context.getString(R.string.cart_courses), json)
                editor.apply()

                val tvCount = cartTabFragment.activity?.findViewById<TextView>(R.id.tv_courses_count)
                tvCount?.text = cartTabFragment.activity?.getString(R.string.txt_cart_course_count, coursesInCart.count().toString())

                notifyItemRemoved(adapterPosition)
                notifyDataSetChanged()
            }
            Glide.with(itemView.context).load(course.imagem)
                .error(R.drawable.ic_launcher_background).into(courseImage)
            courseTitle.text = course.nome
            courseDesc.text = course.descricao
            if (course.precoPromocional.isNullOrEmpty()) {
                fromPrice.text = ""
                finalPrice.text = itemView.context.getString(R.string.txt_final_price, course.preco)
            } else {
                fromPrice.text = itemView.context.getString(R.string.txt_from_price, course.preco)
                finalPrice.text =
                    itemView.context.getString(R.string.txt_final_price, course.precoPromocional)
            }
        }

    }

    /**
     * The RecyclerView ViewHolder class for the cart info resume view
     */
    inner class CartResumeInfoViewHolder(infoView: View) : RecyclerView.ViewHolder(infoView) {
        /**
         * The binding function for the cart info resume view
         */
        fun bindView(viewPager2: ViewPager2?) {
            var total = 0F
            if (coursesInCart?.count()!! > 0) {
                total = coursesInCart.fold(
                    total,
                    { acc, next -> acc + (if (next.precoPromocional.isNullOrEmpty()) next.preco.toFloat() else next.precoPromocional.toFloat()) })
            }
            val tv_total = itemView.findViewById<TextView>(R.id.tv_cart_total_value)
            tv_total.text = itemView.context.getString(R.string.txt_cart_total_value, total)

            val finishBtn = itemView.findViewById<Button>(R.id.btn_finish)
            finishBtn.isEnabled = coursesInCart.count() > 0
            finishBtn.setOnClickListener {
                viewPager2?.currentItem = 1;
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var view: View
        if (viewType == R.layout.rv_cart_course_item) {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.rv_cart_course_item, parent, false)
            return CartCourseCardViewHolder(view)
        } else {
            view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cart_resume_info, parent, false)
            return CartResumeInfoViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            R.layout.rv_cart_course_item -> (holder as CartCourseCardViewHolder).bindView()
            R.layout.cart_resume_info -> (holder as CartResumeInfoViewHolder).bindView(viewPager2)
        }
    }

    override fun getItemCount(): Int {
        // Return the courses in cart count, plus one, for the cart info resume view
        return coursesInCart.size + 1
    }

    override fun getItemViewType(position: Int): Int {
        // Return different view types for the courses card view and the cart info resume view
        return when (position) {
            coursesInCart.size -> R.layout.cart_resume_info
            else -> R.layout.rv_cart_course_item
        }
    }
}