package com.example.mobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val button = findViewById<Button>(R.id.loginmain)
        button.setOnClickListener{
            val intent = Intent(this, loginwin::class.java)
            startActivity(intent)
        }
        val button1 = findViewById<Button>(R.id.registermain)
        button1.setOnClickListener{
            val intent = Intent(this, fillupmain::class.java)
            startActivity(intent)
        }
    }
}