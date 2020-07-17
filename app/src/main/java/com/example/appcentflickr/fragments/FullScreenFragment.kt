package com.example.appcentflickr.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.appcentflickr.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_full_screen.view.*


private const val PHOTO_URL = "photoUrl"

class FullScreenFragment : Fragment() {
    private var PHOTO_URL: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            PHOTO_URL = it.getString(com.example.appcentflickr.fragments.PHOTO_URL)
        }

//        val callback: OnBackPressedCallback =
//            object : OnBackPressedCallback(true ) {
//                override fun handleOnBackPressed() {
//                    Navigation.findNavController(view!!)
//                }
//            }
//        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
//

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_full_screen, container, false)

        Picasso.get().load(PHOTO_URL).into(view.photoImageView)

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(photoUrl: String) =
            FullScreenFragment().apply {
                arguments = Bundle().apply {
                    putString(com.example.appcentflickr.fragments.PHOTO_URL, photoUrl)
                }
            }
    }
}
