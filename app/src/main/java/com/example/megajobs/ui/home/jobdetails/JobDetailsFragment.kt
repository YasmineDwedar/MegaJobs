package com.example.megajobs.ui.home.jobdetails

import BaseFragment
import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.megajobs.R
import com.example.megajobs.databinding.FragmentJobDetailsBinding
import com.example.megajobs.models.Job


class JobDetailsFragment : BaseFragment<FragmentJobDetailsBinding>(R.layout.fragment_job_details) {
    private val args: JobDetailsFragmentArgs by navArgs()
    private lateinit var job: Job
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        job = args.job!!
//        Toast.makeText(requireContext(), job?.company, Toast.LENGTH_SHORT).show()


        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun setListeners() {
        binding?.apply {
            compUrl.setOnClickListener {
                job.company_url?.let { it1 -> openBrowser(it1) }
            }
            jobUrl.setOnClickListener {
                job.url?.let { it1 -> openBrowser(it1) }
            }
        }

    }

    override fun setObservers() {
        binding?.apply {
//            Toast.makeText(requireContext(), job?.company, Toast.LENGTH_SHORT).show()
            compName.text = job?.company
            Glide.with(requireContext()).load(job?.company_logo).placeholder(R.drawable.ic_person)
                .fitCenter().override(500, 500).into(compLogo)
            if(job.company_url!=null){
                underlineUrl(compUrl,job.company_url)
            }
           if (job.url!=null){
               underlineUrl(jobUrl,job.url)
           }

            jobTitle.text = job?.title
            jobType.text = job?.type
            jobDescription.text = Html.fromHtml(job.description)


        }

    }

    override fun removeObservers() {

    }


    fun underlineUrl(view: TextView, text: String?) {
        val underlinedStringtw = SpannableString(text)
        underlinedStringtw.setSpan(UnderlineSpan(), 0, underlinedStringtw.length, 0)
        view.text = underlinedStringtw

    }
    fun openBrowser(url: String) {
        var urll: String = url
        if (!urll.startsWith("http://") && !urll.startsWith("https://")) {
            urll = "http://" + url
        }
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(urll))
        startActivity(browserIntent)

    }

}