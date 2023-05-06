package com.example.deptapp.fragments

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import com.bumptech.glide.Glide
import com.example.deptapp.R
import com.example.deptapp.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    lateinit var binding : FragmentAboutBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding= FragmentAboutBinding.inflate(layoutInflater, container, false)

        Glide.with(binding.root.context).load("https://www.admissionfever.com/Media/clgimg/gallery/2092_img7281787176718769.png").into(binding.aboutImage)
        Glide.with(binding.root.context).load("https://cdn.pixabay.com/photo/2022/06/15/13/39/man-7263965__340.png").into(binding.imgDev1)
        Glide.with(binding.root.context).load("https://cdn.pixabay.com/photo/2022/06/15/13/39/man-7263965__340.png").into(binding.imgDev2)
        Glide.with(binding.root.context).load("https://cdn.pixabay.com/photo/2022/06/15/13/39/man-7263965__340.png").into(binding.imgDev3)
        Glide.with(binding.root.context).load("https://cdn.pixabay.com/photo/2022/06/15/13/39/man-7263965__340.png").into(binding.imgDev4)

        binding.textFace.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse("https://www.facebook.com/people/Hitofficial/100076318810987/"))
        }

        binding.textYoutube.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse("https://www.youtube.com/channel/UChV167CyTOc0ovvu5AO2X3g"))
        }

        binding.textIntsa.setOnClickListener {
            val builder = CustomTabsIntent.Builder()
            val customTabsIntent = builder.build()
            customTabsIntent.launchUrl(binding.root.context, Uri.parse("https://www.instagram.com/hitofficial1/"))
        }

        return binding.root
    }
}