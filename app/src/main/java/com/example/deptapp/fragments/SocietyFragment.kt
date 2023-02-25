package com.example.deptapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.databinding.FragmentLogInBinding
import com.example.deptapp.databinding.FragmentSocietyBinding
import com.google.android.material.navigation.NavigationView


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
                "https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72",
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
        binding.imageSliderSocietyFrag.setImageList(imageList)

        Glide.with(binding.root.context)
            .load("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72")
            .into(binding.imgSocEvent1)
        Glide.with(binding.root.context)
            .load("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72")
            .into(binding.imgSocEvent2)
        binding.tvBtnSocEventViewMore.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", "EVENTS")
            val fragment = EventFragment()
            fragment.arguments = bundle
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame, fragment)
            transaction.commit()
            requireActivity().findViewById<NavigationView>(R.id.navigationView).checkedItem?.isChecked =
                false
        }

        return binding.root
    }

}