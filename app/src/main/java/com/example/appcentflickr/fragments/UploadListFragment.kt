package com.example.appcentflickr.fragments

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appcentflickr.R
import com.example.appcentflickr.adapters.UploadListAdapter
import com.example.appcentflickr.interfaces.OnLoadMoreListener
import com.example.appcentflickr.interfaces.RecyclerViewClickListener
import com.example.appcentflickr.listeners.RecyclerViewLoadMoreScroll
import com.example.appcentflickr.models.Upload
import com.example.appcentflickr.utils.PhotoUrlHelper
import com.example.appcentflickr.view_models.UploadListViewModel
import com.ozzy.kukaf1.models.responses.UploadsResponse
import kotlinx.android.synthetic.main.upload_list_fragment.view.*
import org.koin.android.viewmodel.ext.android.viewModel


class UploadListFragment : Fragment() {

    var loading = false
    var isLastPage = false
    var page = 1
    var status_progress = false;

    var uploadList: MutableList<Upload>? = null
    var uploadListAdapter : UploadListAdapter? = null

    var recyclerView : RecyclerView? = null
    var scrollListener : RecyclerView.OnScrollListener? = null

    companion object {
        fun newInstance() = UploadListFragment()
    }

    val uploadListViewModel: UploadListViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.upload_list_fragment, container, false)


        uploadListViewModel.uploadsResponse().observe(viewLifecycleOwner, Observer<UploadsResponse> { this.consumeUploads(it) })

        setupRecyclerView(view.uploadListRW)

        return view

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    fun setupRecyclerView(recyclerView: RecyclerView) {
        // Adapter build
        uploadList = mutableListOf()

        uploadListViewModel.getUploads(20,page)



        uploadListAdapter = UploadListAdapter(uploadList!!, object : RecyclerViewClickListener {
            override fun recyclerViewListClicked(v: View, position: Int) {
                val photoUrl = PhotoUrlHelper.getImageUrl(uploadList!![position])
                val bundle = Bundle()
                bundle.putString("photoUrl", photoUrl)
                Navigation.findNavController(view!!).navigate(R.id.goToFullScreen, bundle)
            }
        }, context!!)

        val llm = LinearLayoutManager(context!!.applicationContext)



        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val totalItemCount = recyclerView.layoutManager!!.itemCount
                val lastVisibleItemPosition = (recyclerView.layoutManager!! as LinearLayoutManager).findLastVisibleItemPosition()
                if (totalItemCount == lastVisibleItemPosition + 1 && !loading) {
                    loadMoreData()
                }
            }

        }
        recyclerView.addOnScrollListener(scrollListener)

        recyclerView.addOnScrollListener(scrollListener)
        recyclerView.layoutManager = llm
        recyclerView.adapter = uploadListAdapter

    }

    fun loadMoreData(){
        if (!isLastPage){
            loading = true
            uploadListViewModel.getUploads(20,page)
        }

    }

    fun consumeUploads(uploadResponse: UploadsResponse) {
        page++
        isLastPage = uploadResponse.photos!!.total == page
        if (isLastPage)recyclerView!!.removeOnScrollListener(scrollListener!!)
        uploadList!!.addAll(uploadResponse.photos!!.photo!!)
        uploadListAdapter!!.notifyDataSetChanged()
        loading = false

    }


}
