package com.example.megajobs.ui.home.jobs

import BaseFragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.megajobs.R
import com.example.megajobs.adapters.JobAdapter
import com.example.megajobs.databinding.FragmentJobsBinding
import com.example.megajobs.models.Job


class JobsFragment : BaseFragment<FragmentJobsBinding>(R.layout.fragment_jobs),
    JobAdapter.JobsAdapterCallBack {
    val viewModel by lazy {
        ViewModelProvider(this).get(JobsViewModel::class.java)
    }
    val jobAdapter by lazy {
        JobAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setListeners() {

    }

    private fun buildUsersRV() {
        binding?.rvJobs?.adapter = jobAdapter
    }

    override fun setObservers() {
        viewModel.jobsLiveData.observe(this, Observer {

            binding?.progressCircular?.visibility = View.INVISIBLE
            jobAdapter.setDataa(it)
            buildUsersRV()
        })

        viewModel.errorLiveData.observe(this, Observer {
            binding?.progressCircular?.visibility=View.INVISIBLE
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun removeObservers() {
    }

    override fun onUserClick(job: Job) {
        val bundle = Bundle().apply {
            putSerializable("job", job)
        }
        findNavController().navigate(
            JobsFragmentDirections.actionJobsFragmentToJobDetailsFragment(
            job
        ))

    }
}