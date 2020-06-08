package com.jlmcdeveloper.cofresenha.ui.repository.open

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jlmcdeveloper.cofresenha.R
import com.jlmcdeveloper.cofresenha.databinding.FragmentOpenRepositoryBinding
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookActivity
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

        viewModel.startActivity = {
            startActivity(Intent(activity, ListBookActivity::class.java))
            activity?.finish()
        }

        return binding.root
    }

    override fun onStart() {
        viewModel.load()
        super.onStart()
    }

}