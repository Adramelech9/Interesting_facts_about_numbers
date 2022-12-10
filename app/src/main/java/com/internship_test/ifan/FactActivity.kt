package com.internship_test.ifan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView

class FactActivity : AppCompatActivity() {

    private lateinit var fact: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fact)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = TITLE
        fact = findViewById(R.id.fact)
        fact.text = FACT
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        onBackPressed()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val TITLE = "Цікавий факт про число"
        var FACT = ""
    }
}