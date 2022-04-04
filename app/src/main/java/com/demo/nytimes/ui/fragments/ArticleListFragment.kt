package com.demo.nytimes.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.demo.nytimes.R
import com.demo.nytimes.databinding.ArticleListContentBinding
import com.demo.nytimes.databinding.FragmentArticleListBinding
import com.demo.nytimes.model.ArticleDetailsData
import com.demo.nytimes.viewmodel.ArticleViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleListFragment : Fragment() {

    private val articleViewModel: ArticleViewModel by viewModel()

    private var _binding: FragmentArticleListBinding? = null


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArticleListBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.itemList

        val itemDetailFragmentContainer: View? = view.findViewById(R.id.item_detail_nav_container)

        articleViewModel.getAllArticle()
        articleViewModel.articleLiveData.observe(viewLifecycleOwner) {
            setupRecyclerView(recyclerView, itemDetailFragmentContainer, it.results)
        }

    }

    private fun setupRecyclerView(
        recyclerView: RecyclerView,
        itemDetailFragmentContainer: View?,
        articles: ArrayList<ArticleDetailsData>
    ) {
        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(resources.getDrawable(R.drawable.line_seperator))
        recyclerView.addItemDecoration(
            dividerItemDecoration
        )

        recyclerView.adapter = SimpleItemRecyclerViewAdapter(
            requireActivity(), articles, itemDetailFragmentContainer
        )
    }

    class SimpleItemRecyclerViewAdapter(
        private val context: Context,
        private val values: List<ArticleDetailsData>,
        private val itemDetailFragmentContainer: View?
    ) :
        RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

            val binding =
                ArticleListContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            return ViewHolder(binding)

        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = values[position]
            holder.tvArticleTitle.text = item.title
            holder.tvArticleContent.text = item.abstract
            holder.tvArticleDate.text = item.published_date
            holder.tvArticlePublishedBy.text = item.source
            if (item.media != null && item.media.isNotEmpty()) {
                Glide.with(context)
                    .load(item.media[0].mediaMetadata[1].url)
                    .into(holder.imgArticle)
            }
            with(holder.itemView) {
                tag = item
                setOnClickListener { itemView ->
                    val bundle = Bundle()
                    bundle.putParcelable(
                        ArticleDetailFragment.ARG_ITEM,
                        item
                    )
                    if (itemDetailFragmentContainer != null) {
                        itemDetailFragmentContainer.findNavController()
                            .navigate(R.id.fragment_item_detail, bundle)
                    } else {
                        itemView.findNavController().navigate(R.id.show_item_detail, bundle)
                    }
                }
            }
        }

        override fun getItemCount() = values.size

        inner class ViewHolder(binding: ArticleListContentBinding) :
            RecyclerView.ViewHolder(binding.root) {
            val tvArticleTitle: TextView = binding.tvArticleTitle
            val tvArticleContent: TextView = binding.tvArticleContent
            val imgArticle: ImageView = binding.imgArticle
            val tvArticleDate: TextView = binding.tvArticleDate
            val tvArticlePublishedBy: TextView = binding.tvArticlePublishedBy
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}