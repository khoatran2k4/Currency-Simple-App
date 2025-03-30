package com.example.currencyapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val textView1 = findViewById<TextView>(R.id.textView5)
        val textView2 = findViewById<TextView>(R.id.textView6)
        val textView3 = findViewById<TextView>(R.id.textView7)
        val textView4 = findViewById<TextView>(R.id.textView8)
        val textView5 = findViewById<TextView>(R.id.textView9)
        val spinner1 = findViewById<Spinner>(R.id.spinner3)
        val spinner2 = findViewById<Spinner>(R.id.spinner4)
        val button = findViewById<Button>(R.id.button)

        val symbols = resources.getStringArray(R.array.currency_symbols)

        val exchangeRates = arrayOf(
            doubleArrayOf(1.0, 0.9, 110.0, 0.8, 1.4, 1.3, 24000.0),  // USD
            doubleArrayOf(1.1, 1.0, 122.0, 0.89, 1.55, 1.44, 26500.0),  // EUR
            doubleArrayOf(0.009, 0.008, 1.0, 0.007, 0.013, 0.012, 220.0),  // JPY
            doubleArrayOf(1.25, 1.12, 143.0, 1.0, 1.74, 1.62, 29700.0),  // GBP
            doubleArrayOf(0.71, 0.65, 83.0, 0.57, 1.0, 0.93, 17000.0),  // AUD
            doubleArrayOf(0.77, 0.69, 89.0, 0.62, 1.07, 1.0, 18000.0),  // CAD
            doubleArrayOf(0.000042, 0.000038, 0.0045, 0.000034, 0.000059, 0.000056, 1.0)  // VND
        )

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemIdAtPosition(position).toString()
                val symbol = symbols[position]
                textView1.text = symbol
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedItem = parent?.getItemIdAtPosition(position).toString()
                val symbol = symbols[position]
                textView3.text = symbol
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        button.setOnClickListener() {
            val index1 = spinner1.selectedItemPosition
            val index2 = spinner2.selectedItemPosition
            val value1 = spinner1.selectedItem
            val value2 = spinner2.selectedItem
            val rate = exchangeRates[index1][index2]
            val amount = textView2.text.toString().toDoubleOrNull()
            if (amount != null) {
                val result = amount * rate
                textView5.text = "$amount $value1 = ${String.format("%.2f",result)} $value2"
            }

        }

        textView2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val index1 = spinner1.selectedItemPosition
                val index2 = spinner2.selectedItemPosition
                val rate = exchangeRates[index1][index2]
                val amount = s.toString().toDoubleOrNull()
                if (amount != null) {
                    val result = amount * rate
                    textView4.text = "${String.format("%.2f", result)}"
                }
            }
        })



    }
}

