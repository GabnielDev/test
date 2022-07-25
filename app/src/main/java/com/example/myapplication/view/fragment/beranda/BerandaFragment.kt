package com.example.myapplication.view.fragment.beranda

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.FragmentBerandaBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BerandaFragment : Fragment() {

    private var _binding: FragmentBerandaBinding? = null
    private val binding get() = _binding!!

    private val viewModel: BerandaViewModel by viewModels()
    private var page = 1

    private lateinit var nowplayingAdapter: BerandaAdapter
    private lateinit var topratedAdapter: BerandaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentBerandaBinding.inflate(inflater, container, false)
        val view = binding.root

        getData()
        setupAdapter()

        return view
    }

    private fun setupAdapter() {
        binding.shimmerFrameLayout.apply {
            visibility = VISIBLE
            startShimmerAnimation()
        }

        nowplayingAdapter = BerandaAdapter(ArrayList())
        binding.layoutNowPlaying.apply {
            txtCategory.text = "Now Playing"
            rvBeranda.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = nowplayingAdapter
            }
        }

        topratedAdapter = BerandaAdapter(ArrayList())
        binding.layoutTopRated.apply {
            txtCategory.text = "Top Rated"
            rvBeranda.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = topratedAdapter
            }
        }

    }

    private fun getData() {
        viewModel.getNowPlaying(page).observe(viewLifecycleOwner) {
            it?.results.let { data ->
                nowplayingAdapter.setData(data)
                binding.shimmerFrameLayout.apply {
                    visibility = GONE
                    stopShimmerAnimation()
                }
            }
        }

        viewModel.getTopRated(page).observe(viewLifecycleOwner) {
            it?.results.let { data ->
                topratedAdapter.setData(data)

            }
        }

    }

}