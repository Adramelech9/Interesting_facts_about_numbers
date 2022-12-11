package com.internship_test.ifan

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.internship_test.ifan.adapter.FactAdapter
import com.internship_test.ifan.databinding.ActivityMainBinding
import com.internship_test.ifan.model.Fact
import com.internship_test.ifan.model.entity.FactEntity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class MainActivity : AppCompatActivity(), FactAdapter.Listener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val db = Fact.getData(this)

        binding.inputNum.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                binding.getFact.isEnabled = binding.inputNum.text.isNotEmpty()
            }
        })
        binding.getFact.setOnClickListener {
            getFact("$URL/${binding.inputNum.text}", db)
        }
        binding.getRandomFact.setOnClickListener {
            getFact(RANDOM_URL, db)
        }
        val listFact = ArrayList<String>()
        db.getDao().getAllFact().asLiveData().observe(this) { list ->
            listFact.clear()
            list.forEach {
                listFact.add(it.fact)
            }
            binding.rv.layoutManager = LinearLayoutManager(this)
            binding.rv.adapter = FactAdapter(this, listFact)
        }
    }

    private fun getFact(url: String, db: Fact) {
        GlobalScope.launch {
            FactActivity.FACT = URL(url).readText()
            val fact = FactEntity(null, FactActivity.FACT)
            db.getDao().insertFact(fact)
            start()
        }
    }

    companion object {
        private const val URL = "http://numbersapi.com"
        private const val RANDOM_URL = "$URL/random/math"
    }

    override fun start() {
        startActivity(Intent(this@MainActivity, FactActivity::class.java))
    }
}