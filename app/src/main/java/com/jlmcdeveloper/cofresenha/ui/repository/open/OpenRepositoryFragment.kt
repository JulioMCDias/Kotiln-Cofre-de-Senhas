package com.jlmcdeveloper.cofresenha.ui.repository.open

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.jlmcdeveloper.cofresenha.databinding.FragmentOpenRepositoryBinding
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookActivity
import com.jlmcdeveloper.cofresenha.utils.Utils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class OpenRepositoryFragment : Fragment() {
    private val viewModel: OpenRepositoryViewModel by inject()

    companion object {
        fun newInstance() =
            OpenRepositoryFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                        savedInstanceState: Bundle?): View? {

        val binding : FragmentOpenRepositoryBinding =
            FragmentOpenRepositoryBinding.inflate(inflater, container, false)

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        // ----- iniciar lista de cadenos ------
        viewModel.startActivity = {
            startActivity(Intent(activity, ListBookActivity::class.java))
            activity?.finish()
        }

        // ------ erro na senha --------
        viewModel.error = {
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(context?.applicationContext, "senha invalida", Toast.LENGTH_LONG)
                    .show()
            }
        }


        binding.buttonOpenReposi.setOnClickListener {
            Utils.closeKeyboard(context!!, binding.textInputEditTextPassword)
            viewModel.openRepository()
        }

        return binding.root
    }

    override fun onStart() {
        viewModel.load()
        super.onStart()
    }

}