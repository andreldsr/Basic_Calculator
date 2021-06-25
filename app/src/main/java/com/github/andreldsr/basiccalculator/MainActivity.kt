package com.github.andreldsr.basiccalculator

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.andreldsr.basiccalculator.util.KeyboardUtil

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var spinner: Spinner
    private lateinit var etOperator1: EditText
    private lateinit var etOperator2: EditText
    private lateinit var tvResult: TextView
    private lateinit var tvResultLabel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide();
        supportActionBar?.hide();
        bindViews()
        createSpinner()
        bindListeners()
    }

    private fun bindViews() {
        etOperator1 = findViewById(R.id.et_operator1)
        etOperator2 = findViewById(R.id.et_operator2)
        spinner = findViewById(R.id.spinner)
        button = findViewById(R.id.button)
        tvResult = findViewById(R.id.tv_result)
        tvResultLabel = findViewById(R.id.tv_result_label)
    }

    private fun createSpinner() {
        ArrayAdapter.createFromResource(
            this,
            R.array.operacoes_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun bindListeners() {
        button.setOnClickListener(View.OnClickListener {
            try {
                KeyboardUtil.hideKeyboard(this)
                val val1 = etOperator1.text.toString().toDouble()
                val val2 = etOperator2.text.toString().toDouble()
                tvResult.text = calculate(val1, val2).toString()
                tvResultLabel.visibility = View.VISIBLE
            } catch (e: NumberFormatException) {
                etOperator1.setText("")
                etOperator2.setText("")
            }
        })
    }

    private fun calculate(val1: Double, val2: Double): Double {
        return when (spinner.selectedItem as String) {
            "SOMA" -> val1 + val2
            "SUBTRACAO" -> val1 - val2
            "MULTIPLICACAO" -> val1 * val2
            "DIVISAO" -> val1 / val2
            else -> 0.0
        }
    }
}