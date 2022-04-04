package com.demo.nytimes.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.demo.nytimes.databinding.FragmentArticleDetailBinding
import com.demo.nytimes.model.ArticleDetailsData

class ArticleDetailFragment : Fragment() {

    private lateinit var articleData: ArticleDetailsData

    private var _binding: FragmentArticleDetailBinding? = null

    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        articleData = arguments?.getParcelable(ARG_ITEM)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleDetailBinding.inflate(inflater, container, false)
        val rootView = binding.root
        updateContent()

        return rootView
    }

    private fun updateContent() {
        binding.tvPublishDate?.text = articleData.published_date
        binding.tvPublishedBy?.text = articleData.source
        binding.tvArticleContent?.text =
            "${articleData.abstract}\n\n${articleData.abstract}\n\n${articleData.abstract}"
        binding.tvArticleTitle?.text = articleData.title
        Glide.with(requireActivity())
            .load(articleData.media[0].mediaMetadata[2].url)
            .into(binding.imgArticle!!)
    }

    companion object {

        const val ARG_ITEM = "item"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}