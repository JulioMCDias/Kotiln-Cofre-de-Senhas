package com.jlmcdeveloper.cofresenha.ui.listpassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.ui.addpassword.AddPasswordActivity
import kotlinx.android.synthetic.main.activity_list_password.*

class ListPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_password)

        btn_list_password.setOnClickListener {
            startActivity(Intent(this, AddPasswordActivity::class.java))
        }
    }
}