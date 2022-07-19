package com.app.rupiahformater

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var etRupiah = findViewById<EditText>(R.id.etRupiah)
        var current = ""

        etRupiah.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val stringText = s.toString()

                if(stringText != current) {
                    etRupiah.removeTextChangedListener(this)

                    val locale: Locale = Locale("id", "ID")
                    val cleanString = stringText.replace("[Rp,.]".toRegex(), "")
                    val parsed = cleanString.toDouble()
                    val formatted = NumberFormat.getCurrencyInstance(locale).format(parsed / 100)

                    current = formatted
                    etRupiah.setText(formatted)
                    etRupiah.setSelection(formatted.length)
                    etRupiah.addTextChangedListener(this)
                }
            }
        })

    }


}