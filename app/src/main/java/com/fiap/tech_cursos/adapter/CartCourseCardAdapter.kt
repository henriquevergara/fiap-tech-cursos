package com.fiap.tech_cursos.adapter

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
import com.fiap.tech_cursos.model.Curso

/**
 *  Cart courses list RecyclerView adapter class
 */
class CartCourseCardAdapter(
    private val coursesInCart: List<Curso>,
    private val viewPager2: ViewPager2?
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    /**
     * The RecyclerView ViewHolder class for the courses card view
     */
    class CartCourseCardViewHolder(courseView: View) : RecyclerView.ViewHolder(courseView) {
        /**
         * The binding function for the courses card view
         */
        fun bindView(course: Curso) {
            val courseImage = itemView.findViewById<ImageView>(R.id.iv_cart_course_image)
            val courseTitle = itemView.findViewById<TextView>(R.id.tv_course_title)
            val courseDesc = itemView.findViewById<TextView>(R.id.tv_course_desc)
            val fromPrice = itemView.findViewById<TextView>(R.id.tv_from_price)
            val finalPrice = itemView.findViewById<TextView>(R.id.tv_final_price)
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
    class CartResumeInfoViewHolder(infoView: View) : RecyclerView.ViewHolder(infoView) {
        /**
         * The binding function for the cart info resume view
         */
        fun bindView(coursesInCart: List<Curso>, viewPager2: ViewPager2?) {
            var total = 0F
            if (coursesInCart?.count()!! > 0) {
                total = coursesInCart.fold(
                    total,
                    { acc, next -> acc + (if (next.precoPromocional.isNullOrEmpty()) next.preco.toFloat() else next.precoPromocional.toFloat()) })
            }
            val tv_total = itemView.findViewById<TextView>(R.id.tv_cart_total_value)
            tv_total.text = itemView.context.getString(R.string.txt_cart_total_value, total)

            val finishBtn = itemView.findViewById<Button>(R.id.btn_finish)
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
            R.layout.rv_cart_course_item -> {
                val course = coursesInCart[position]
                (holder as CartCourseCardViewHolder).bindView(course)
            }
            R.layout.cart_resume_info -> (holder as CartResumeInfoViewHolder).bindView(
                coursesInCart,
                viewPager2
            )
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