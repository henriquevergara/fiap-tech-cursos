package com.fiap.tech_cursos.fragments

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.fiap.tech_cursos.R
import com.fiap.tech_cursos.activities.PainelActivity
import com.fiap.tech_cursos.databinding.FragmentPaymentTabBinding

class PaymentTabFragment : Fragment() {
    private var _binding: FragmentPaymentTabBinding? = null
    private val binding get() = _binding!!

    /**
     * Initialize the payment fragment
     */
    private fun initialize() {
        val finishPaymentBtn = binding.root.findViewById<Button>(R.id.btn_finish_payment)
        val backBtn = binding.root.findViewById<Button>(R.id.btn_back)
        val radioGroup = binding.root.findViewById<RadioGroup>(R.id.rg_payment_methods)
        finishPaymentBtn?.isEnabled = radioGroup?.checkedRadioButtonId != -1

        radioGroup?.setOnCheckedChangeListener { group, _ -> finishPaymentBtn?.isEnabled = group?.checkedRadioButtonId != -1 }

        backBtn?.setOnClickListener {
            val viewPager2 = this.activity?.findViewById<ViewPager2>(R.id.cart_viewpager)
            viewPager2?.currentItem = 0;
        }

        finishPaymentBtn?.setOnClickListener {
            val prefs: SharedPreferences? = this.activity?.getSharedPreferences(getString(R.string.SHARED_PREFERENCES), Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor? = prefs?.edit()
            editor?.putString(getString(R.string.cart_courses), "")
            editor?.apply()

            Toast.makeText(binding.root.context, "Compra efetuada com sucesso", Toast.LENGTH_SHORT).show()

            val intent = Intent(binding.root.context, PainelActivity::class.java)
            val thread: Thread = object : Thread() {
                override fun run() {
                    try {
                        sleep(2000) // As I am using LENGTH_SHORT in Toast
                        startActivity(intent)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }

            thread.start()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPaymentTabBinding.inflate(inflater, container, false)
        val view = binding.root
        initialize()
        return view
    }
}