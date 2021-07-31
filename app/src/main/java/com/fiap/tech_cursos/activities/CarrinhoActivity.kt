package com.fiap.tech_cursos.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.adapter.CartTabsPagerAdapter
import com.fiap.tech_cursos.databinding.ActivityCarrinhoBinding
import com.google.android.material.tabs.TabLayoutMediator

// Carrinho Activity
class CarrinhoActivity : AppCompatActivity() {
    lateinit var binding: ActivityCarrinhoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCarrinhoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val viewPager = binding.cartViewpager
        val tabLayout = binding.tlCartSteps

        val adapter = CartTabsPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = adapter
        viewPager.setUserInputEnabled(false) // Disable viewpager2 swipe to change fragment

        TabLayoutMediator(tabLayout, viewPager) {tab, position ->
            // Set the tabs title text and icon
            if (position == 0) {
                tab.text = getString(R.string.txt_tabitem_cart)
                tab.icon = getDrawable(R.drawable.ic_baseline_shopping_cart_24)
            } else {
                tab.text = getString(R.string.txt_tabitem_payment)
                tab.icon = getDrawable(R.drawable.ic_baseline_monetization_on_24)
            }
        }.attach()
    }
}