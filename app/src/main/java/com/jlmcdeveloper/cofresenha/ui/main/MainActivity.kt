package com.jlmcdeveloper.cofresenha.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.data.fileNameRepository
import com.jlmcdeveloper.cofresenha.data.mimeType
import com.jlmcdeveloper.cofresenha.ui.repository.RepositoryActivity
import com.jlmcdeveloper.cofresenha.utils.OpenFileFramework
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel  by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        animation()

        var newRepository = false

        button_open.setOnClickListener {
            OpenFileFramework.editDocument(mimeType, this)
            newRepository = false
        }


        button_new_repository.setOnClickListener {
            OpenFileFramework.createFile(mimeType, fileNameRepository, this)
            newRepository = true
        }


        viewModel.startActivity = {
            startActivity(Intent(this, RepositoryActivity::class.java)
                .putExtra("NewRepository", newRepository)
            )
            finish()
        }

    }

    private fun animation(){
        image_cofre.startAnimation(AnimationUtils.loadAnimation(this, R.anim.translate))
        text_repository.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        button_open.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
        button_new_repository.startAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in))
    }




    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if ((requestCode == OpenFileFramework.EDIT_REQUEST_CODE ||
                    requestCode == OpenFileFramework.WRITE_REQUEST_CODE) &&
                    resultCode == Activity.RESULT_OK) {

            data?.data?.also { uri ->
                viewModel.setFile(uri, OpenFileFramework.dumpMetaData(uri, this))
                viewModel.openFile()
            }
        }


    }
}
