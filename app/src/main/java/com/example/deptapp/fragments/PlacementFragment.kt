package com.example.deptapp.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.deptapp.R
import com.example.deptapp.databinding.FragmentPlacementBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class PlacementFragment : Fragment() {

    private lateinit var binding: FragmentPlacementBinding
    private lateinit var pieChart: PieChart

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlacementBinding.inflate(layoutInflater, container, false)

        pieChart = binding.placementPieChart
        setupPieChart()
        loadPieChartData()

        return binding.root
    }

    private fun setupPieChart() {
        pieChart.isDrawHoleEnabled = true
        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelTextSize(12f)
        pieChart.setEntryLabelColor(Color.BLACK)
        pieChart.centerText = "BATCH\n2018-2022"
        val r = resources.getFont(R.font.serif)
        pieChart.setCenterTextTypeface(r)
        pieChart.setCenterTextColor(Color.parseColor("#4E5180"))
        pieChart.setCenterTextSize(30f)
        pieChart.description.isEnabled = false
        pieChart.setExtraOffsets(5f,20f,5f,20f)

        val l = pieChart.legend
        l.isEnabled = false
    }

    private fun loadPieChartData() {

        val entries: ArrayList<PieEntry> = ArrayList()

        entries.add(PieEntry(0.1f, "Assenture"))
        entries.add(PieEntry(0.15f, "TCS"))
        entries.add(PieEntry(0.10f, "Infosys"))
        entries.add(PieEntry(0.25f, "Wipro"))
        entries.add(PieEntry(0.2f, "Capgimini"))
        entries.add(PieEntry(0.2f, "Others"))

        val colors: ArrayList<Int> = ArrayList()
        for (color in ColorTemplate.MATERIAL_COLORS) {
            colors.add(color)
        }

        for (color in ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color)
        }

        val dataSet = PieDataSet(entries, "Batch")
        dataSet.colors = colors

        val data = PieData(dataSet)
        data.setDrawValues(true)
        data.setValueFormatter(PercentFormatter(pieChart))
        data.setValueTextSize(12f)
        data.setValueTextColor(Color.BLACK)

        pieChart.data = data
        pieChart.invalidate()

        pieChart.animateY(1400, Easing.EaseInOutQuad)
    }

}