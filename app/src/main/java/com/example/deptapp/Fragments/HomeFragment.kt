package com.example.deptapp.Fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.contains
import com.bumptech.glide.Glide
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.example.deptapp.R
import com.example.deptapp.databinding.FragmentHomeBinding
import kotlinx.coroutines.flow.combine

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    lateinit var binding : FragmentHomeBinding
    lateinit var imageList: ArrayList<SlideModel>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        setUp()

        binding.marqueeText.isSelected = true
        imageList= ArrayList()

        imageList.add(SlideModel("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png","Haldia Institute Of Technology"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fthree-min.JPG?alt=media&token=26feb110-4f7b-45eb-8ec2-80769de26f44","ACM Hack Track Workshop"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ffour-min.JPG?alt=media&token=ad0aace8-0686-4ef8-8852-6e3fb4867c72","ACM Members"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Fone-min.JPG?alt=media&token=c5e88727-c810-42d9-886f-9953073ec654","Prize Distribution"))
        imageList.add(SlideModel("https://firebasestorage.googleapis.com/v0/b/social-media-2-0.appspot.com/o/images%2Ftwo.JPG?alt=media&token=059ccf95-ede1-4787-a833-caaec8e56a91","Workshop"))
        binding.imageSlider.setImageList(imageList,ScaleTypes.CENTER_CROP)
        return binding.root
    }

    private fun setUp(){
        Glide.with(this).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.imgEvent1)
        Glide.with(this).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.imgEvent2)
        Glide.with(this).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.imgEvent3)
        Glide.with(this).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.imgEvent4)
        Glide.with(this).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.imgEvent5)


    }

}