package com.jlmcdeveloper.cofresenha.ui.repository.create

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jlmcdeveloper.cofresenha.databinding.FragmentCreateRepositoryBinding
import com.jlmcdeveloper.cofresenha.ui.listbook.ListBookActivity
import org.koin.android.ext.android.inject

class CreateRepositoryFragment : Fragment() {
    private  val viewModel: CreateRepositoryViewModel by inject()

    companion object {
        fun newInstance() =
            CreateRepositoryFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding : FragmentCreateRepositoryBinding =
            FragmentCreateRepositoryBinding.inflate(inflater, container, false)

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