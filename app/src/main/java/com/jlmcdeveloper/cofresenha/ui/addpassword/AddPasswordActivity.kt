package com.jlmcdeveloper.cofresenha.ui.addpassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.databinding.ActivityAddPasswordBinding
import org.koin.android.ext.android.inject

class AddPasswordActivity : AppCompatActivity() {
    private val viewModel: AddPasswordViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_password)

        val binding: ActivityAddPasswordBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_add_password)

        setSupportActionBar(binding.toolbarAddPassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.finish = {
            finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return true
    }
}