package com.internship_test.ifan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.FrameLayout
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity() {

    private lateinit var inputNum: EditText
    private lateinit var fact: Button
    private lateinit var randomFact: Button
    private lateinit var recyclerView: FrameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputNum = findViewById(R.id.input_num)
        fact = findViewById(R.id.get_fact)
        randomFact = findViewById(R.id.get_random_fact)
        recyclerView = findViewById(R.id.rv)

        inputNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                fact.isEnabled = inputNum.text.isNotEmpty()
            }
        })
        fact.setOnClickListener {
            GlobalScope.launch {
                getFact("$URL/${inputNum.text}")
            }
        }
        randomFact.setOnClickListener {
            GlobalScope.launch {
                getFact(RANDOM_URL)
            }
        }
        /*supportFragmentManager.beginTransaction().apply {
            replace(R.id.rv, HistoryFragment(URL(RANDOM_URL).readText()))
            addToBackStack(null)
            commit()
        }*/
    }

    private fun getFact(url: String) {
        FactActivity.FACT = URL(url).readText()
        startActivity(Intent(this@MainActivity, FactActivity::class.java))
    }

    companion object {
        private const val URL = "http://numbersapi.com"
        private const val RANDOM_URL = "$URL/random/math"
    }
}