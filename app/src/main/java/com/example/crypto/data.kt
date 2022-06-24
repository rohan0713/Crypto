package com.example.crypto

import android.graphics.Color
import com.tradingview.lightweightcharts.api.chart.models.color.toIntColor
import com.tradingview.lightweightcharts.api.interfaces.SeriesApi
import com.tradingview.lightweightcharts.api.options.models.layoutOptions
import com.tradingview.lightweightcharts.api.options.models.localizationOptions
import com.tradingview.lightweightcharts.api.series.common.PriceFormatter
import com.tradingview.lightweightcharts.api.series.models.HistogramData
import com.tradingview.lightweightcharts.api.series.models.Time
import com.tradingview.lightweightcharts.api.series.models.WhitespaceData
import com.tradingview.lightweightcharts.runtime.plugins.DateTimeFormat
import com.tradingview.lightweightcharts.runtime.plugins.TimeFormatter
import com.tradingview.lightweightcharts.view.ChartsView

class data() {

    fun fetch(chart : ChartsView, list : List<candle_data>){

        chart.api.applyOptions {
            layout = layoutOptions {
                backgroundColor = Color.LTGRAY.toIntColor()
                textColor = Color.BLACK.toIntColor()
            }
        }

        var histogramSeries: SeriesApi? = null
        chart.api.addCandlestickSeries(
            onSeriesCreated = { series ->
                histogramSeries = series
            }
        )

        val data = listOf(
            HistogramData(Time.BusinessDay(2019, 6, 11), 40.01f),
            HistogramData(Time.BusinessDay(2019, 6, 12), 52.38f),
            HistogramData(Time.BusinessDay(2019, 6, 13), 36.30f),
            HistogramData(Time.BusinessDay(2019, 6, 14), 34.48f),
            WhitespaceData(Time.BusinessDay(2019, 6, 15)),
            WhitespaceData(Time.BusinessDay(2019, 6, 16)),
            HistogramData(Time.BusinessDay(2019, 6, 17), 41.50f),
            HistogramData(Time.BusinessDay(2019, 6, 18), 34.82f)
        )

        histogramSeries?.setData(data)
    }
}
