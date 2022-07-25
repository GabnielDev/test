package com.example.myapplication.view.fragment.segera

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentSegeraBinding
import com.example.myapplication.model.ResultsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SegeraFragment : Fragment(), SegeraAdapter.OnItemClickCallback {

    private val viewModel: SegeraViewModel by viewModels()

    private var _binding: FragmentSegeraBinding? = null
    private val binding get() = _binding!!

    private lateinit var segeraAdapter: SegeraAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        _binding = FragmentSegeraBinding.inflate(inflater, container, false)
        val view = binding.root

        getData()
        setupAdater()

        return view
    }

    private fun setupAdater() {
        segeraAdapter = SegeraAdapter(ArrayList(), this)
        binding.rvSegera.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = segeraAdapter
        }

    }

    private fun getData() {
        viewModel.getSegera(page = 1).observe(viewLifecycleOwner) {
            it?.results.let {
                segeraAdapter.setData(it)
            }
        }
    }

    override fun onItemClick(data: ResultsItem?) {
        viewModel.getTrailer(data?.id).observe(this) {
            val trailerId = it?.get(0)?.key
                startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$trailerId"))
                        .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                )
        }

    }
}