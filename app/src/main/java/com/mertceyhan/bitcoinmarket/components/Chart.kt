package com.mertceyhan.bitcoinmarket.components

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineDataSet
import com.google.accompanist.placeholder.placeholder
import com.mertceyhan.R
import com.mertceyhan.bitcoinmarket.core.ui.ShimmerProperty
import com.mertceyhan.bitcoinmarket.utils.extensions.darkModeEnabled
import com.mertceyhan.bitcoinmarket.utils.extensions.getCompatColor
import com.mertceyhan.bitcoinmarket.utils.extensions.setLineDataSet

@Composable
fun Chart(
    modifier: Modifier = Modifier,
    lineDataSet: LineDataSet? = null,
    isShowShimmer: Boolean
) {
    AndroidView(
        factory = { context ->
            LineChart(context).apply {
                description.isEnabled = false
                isDragEnabled = false
                xAxis.isEnabled = false
                axisLeft.setDrawAxisLine(false)
                axisLeft.textColor = getChartAxisLeftTextColor(context = context)
                axisRight.isEnabled = false
                legend.isEnabled = false
                setTouchEnabled(false)
                setScaleEnabled(false)
                setDrawGridBackground(false)
                setDrawBorders(false)
                invalidate()

                setLineDataSet(lineDataSet = lineDataSet)
            }
        },
        modifier = modifier
            .fillMaxWidth()
            .requiredHeight(300.dp)
            .placeholder(
                visible = isShowShimmer,
                color = ShimmerProperty.color,
                shape = ShimmerProperty.shape,
                highlight = ShimmerProperty.highlight
            )
    )
}

private fun getChartAxisLeftTextColor(context: Context): Int =
    if (context.darkModeEnabled()) {
        context.getCompatColor(R.color.white)
    } else {
        context.getCompatColor(R.color.gray_950)
    }