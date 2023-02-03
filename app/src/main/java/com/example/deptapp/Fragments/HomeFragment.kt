package com.example.deptapp.Fragments

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.contains
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

        binding.marqueeText.isSelected = true
        imageList= ArrayList()

        imageList.add(SlideModel(R.drawable.four,"Haldia Institute Of Technology"))
        imageList.add(SlideModel(R.drawable.three,"ACM Hack Track Workshop"))
        imageList.add(SlideModel(R.drawable.one,"ACM Members"))
        imageList.add(SlideModel(R.drawable.four,"Prize Distribution"))
        imageList.add(SlideModel(R.drawable.two,"Workshop"))
        binding.imageSlider.textAlignment=View.TEXT_ALIGNMENT_CENTER
        binding.imageSlider.setImageList(imageList,ScaleTypes.CENTER_CROP)
        return binding.root
    }

}