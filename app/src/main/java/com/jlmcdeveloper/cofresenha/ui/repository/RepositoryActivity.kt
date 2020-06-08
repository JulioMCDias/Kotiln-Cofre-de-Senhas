package com.jlmcdeveloper.cofresenha.ui.repository

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.ui.repository.create.CreateRepositoryFragment
import com.jlmcdeveloper.cofresenha.ui.repository.open.OpenRepositoryFragment

class RepositoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repository)


        if(intent.getBooleanExtra("NewRepository", true))
            createRepositoryFragment()
        else
            openRepositoryFragment()


    }

    private fun createRepositoryFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_repository, CreateRepositoryFragment.newInstance()).commit()
    }

    private fun openRepositoryFragment() {
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_repository, OpenRepositoryFragment.newInstance()).commit()
    }
}