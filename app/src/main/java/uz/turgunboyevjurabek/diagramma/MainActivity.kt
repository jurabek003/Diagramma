package uz.turgunboyevjurabek.diagramma

import android.R
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.anychart.charts.Pie
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.MPPointF
import uz.turgunboyevjurabek.diagramma.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.SimpleTimeZone


class MainActivity : AppCompatActivity() {

    lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    lateinit var barEntriesList: ArrayList<BarEntry>

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var pieChart: PieChart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        barChart = binding.barChartView

        // on below line we are calling get bar
        // chart data to add data to our array list
        val barChart: BarChart = binding.barChartView

        // Ma'lumotlar ro'yxati (date va qiymatlar)
        val date=SimpleDateFormat("MM").format(Date())

        Toast.makeText(this, "$date", Toast.LENGTH_SHORT).show()

        val dates = listOf("January", "February", "March", "April","May","June","July","August","September","October","November","December")
        val values = listOf(10f, 50f, 700f, 1200f,3400f,2134f,5657f,66666f,9999f,1212f,222222f,1212f)
        diagram1()
        // BarEntry ma'lumotlarini tayyorlash
        val entries: ArrayList<BarEntry> = ArrayList()
        for (i in values.indices) {
            entries.add(BarEntry(i.toFloat(), values[i]))
        }

        // X-osi bo'yicha datalarni joylash
        val barDataSet = BarDataSet(entries, "Ma'lumotlar")
        barDataSet.color = Color.BLUE
        // X-osi bo'yicha nomlarni joylash
        val xAxisLabels = dates.toTypedArray()
        val xAxis = barChart.xAxis
        xAxis.valueFormatter = IndexAxisValueFormatter(xAxisLabels)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.isGranularityEnabled = true

        // BarChart konfiguratsiyalari
        val barData = BarData(barDataSet)
        barChart.data = barData
        barChart.setFitBars(true)
        barChart.description.isEnabled = false
        barChart.animateY(1000)

        // Barchartni yangilash
        barChart.invalidate()
    }

    private fun diagram1() {
        pieChart = binding.anyChartView

        // on below line we are setting user percent value,
        // setting description as enabled and offset for pie chart
        pieChart.setUsePercentValues(true)
        pieChart.getDescription().setEnabled(true)
        pieChart.setExtraOffsets(5f, 10f, 5f, 5f)

        // on below line we are setting drag for our pie chart
        pieChart.setDragDecelerationFrictionCoef(0.95f)

        // o'rtagi yumaloq ni bor bulishi yoki bor bo'lmasligi
        pieChart.setDrawHoleEnabled(false)
        pieChart.setHoleColor(Color.WHITE)

        // on below line we are setting circle color and alpha
        pieChart.setTransparentCircleColor(Color.YELLOW)
        pieChart.setTransparentCircleAlpha(110)

        // o'rtadagi yumaloqni size zi
        pieChart.setHoleRadius(0f)
        pieChart.setTransparentCircleRadius(0f)

        // on below line we are setting center text
        pieChart.setDrawCenterText(true)

        // on below line we are setting
        // rotation for our pie chart
        pieChart.setRotationAngle(0f)

        // enable rotation of the pieChart by touch
        pieChart.setRotationEnabled(true)
        pieChart.setHighlightPerTapEnabled(true)

        // on below line we are setting animation for our pie chart
        pieChart.animateY(1400, Easing.EaseInOutQuad)

        // on below line we are disabling our legend for pie chart
        pieChart.legend.isEnabled = true
        pieChart.setEntryLabelColor(Color.WHITE)
        pieChart.setEntryLabelTextSize(12f)

        // on below line we are creating array list and
        // adding data to it to display in pie chart
        val entries: ArrayList<PieEntry> = ArrayList()
        entries.add(PieEntry(70f))
        entries.add(PieEntry(20f))
        entries.add(PieEntry(10f))

        // on below line we are setting pie data set
        val dataSet = PieDataSet(entries, "$entries")

        // on below line we are setting icons.
        dataSet.setDrawIcons(true)

        // on below line we are setting slice for pie
        dataSet.sliceSpace = 3f
        dataSet.iconsOffset = MPPointF(0f, 40f)
        dataSet.selectionShift = 20f

        // add a lot of colors to list
        val colors: ArrayList<Int> = ArrayList()
        colors.add(resources.getColor(R.color.holo_purple))
        colors.add(resources.getColor(R.color.holo_blue_bright))
        colors.add(resources.getColor(R.color.darker_gray))

        // on below line we are setting colors.
        dataSet.colors = colors

        // on below line we are setting pie data set
        val data = PieData(dataSet)
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(15f)
        data.setValueTypeface(Typeface.SANS_SERIF)
        data.setValueTextColor(Color.WHITE)
        pieChart.setData(data)

        // undo all highlights
        pieChart.highlightValues(null)

        // loading chart
        pieChart.invalidate()


    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        // on below line we are adding data
        // to our bar entries list
        barEntriesList.add(BarEntry(1f, 5f))
        barEntriesList.add(BarEntry(2f, 4f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 2f))
        barEntriesList.add(BarEntry(5f, 1f))

    }
}
