package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.databinding.FragmentLogInBinding
import com.example.deptapp.databinding.FragmentSocietyBinding


class SocietyFragment : Fragment() {

    lateinit var binding: FragmentSocietyBinding
    lateinit var imageList: ArrayList<SlideModel>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         binding = FragmentSocietyBinding.inflate(layoutInflater, container, false)
        imageList = ArrayList()

        imageList.add(
            SlideModel(
                "https://pict.acm.org/pulzion19/About-us/data1/ACMlogos.png",
                "Welcome To ACM Chapter", ScaleTypes.CENTER_INSIDE

            )
        )
        imageList.add(
            SlideModel(
                "https://firebasesztorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fthree-min.JPG?alt=media&token=26feb110-4f7b-45eb-8ec2-80769de26f44",
                "ACM Hack Track Workshop", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
                "ACM Members", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fone-min.JPG?alt=media&token=c5e88727-c810-42d9-886f-9953073ec654",
                "Prize Distribution", ScaleTypes.CENTER_CROP
            )
        )
        imageList.add(
            SlideModel(
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ftwo.JPG?alt=media&token=059ccf95-ede1-4787-a833-caaec8e56a91",
                "Workshop", ScaleTypes.CENTER_CROP
            )
        )
        binding.imageSlidersocietyFrag.setImageList(imageList)
        return binding.root
    }

    /*companion object {
        *//**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SocietyFragment.
         *//*
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SocietyFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }*/
}