package com.jlmcdeveloper.cofresenha.ui.repository

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookActivity
import kotlinx.android.synthetic.main.activity_repository.*

class RepositoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)

        button_open.setOnClickListener {
            startActivity(Intent(this, ListBookActivity::class.java))
            finish()
        }
    }
}