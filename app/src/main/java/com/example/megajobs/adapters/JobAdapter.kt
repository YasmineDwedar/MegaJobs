package com.example.megajobs.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.megajobs.R
import com.example.megajobs.databinding.JobItemBinding
import com.example.megajobs.models.Job


class JobAdapter(val jobsAdapterCallBack: JobsAdapterCallBack) : RecyclerView.Adapter<JobAdapter.ViewHolder>() {
    private var users = ArrayList<Job>()
    private lateinit var context: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(JobItemBinding.inflate(LayoutInflater.from(context)))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.apply {
            compLogo.scaleType = ImageView.ScaleType.CENTER_CROP
            compName.text = users[position].company
            compJobTitle.text = users[position].title
            Glide.with(context).load(users[position].company_logo).placeholder(R.drawable.ic_person).fitCenter().override(500, 500).into(compLogo)
            root.setOnClickListener {
                jobsAdapterCallBack.onUserClick(users[position])

            }
        }

    }

    override fun getItemCount(): Int {
        return users.size
    }

    fun setDataa(users: List<Job>) {
        this.users.clear()
        this.users.addAll(users)
        users.toCollection(this.users)
        notifyDataSetChanged()
    }

    inner class ViewHolder(val binding: JobItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    interface JobsAdapterCallBack {
        fun onUserClick(job: Job)
    }
}