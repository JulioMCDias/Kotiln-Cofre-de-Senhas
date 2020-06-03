package com.jlmcdeveloper.cofresenha.ui.listbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.ui.listpassword.ListPasswordActivity
import kotlinx.android.synthetic.main.activity_list_book.*

class ListBookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_book)

        btn_list_book.setOnClickListener {
            startActivity(Intent(this, ListPasswordActivity::class.java))
        }
    }
}