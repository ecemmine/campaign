package com.android.campaign.ui.base

import com.android.campaign.R
import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.counter.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import android.os.CountDownTimer
import java.time.OffsetDateTime

import java.util.concurrent.TimeUnit


class Counter@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null

): RelativeLayout(context, attrs) {

    init {
        LayoutInflater.from(context)
            .inflate(R.layout.counter, this, true)

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.CounterView, 0, 0)

            val title = resources.getText(typedArray
                .getResourceId(R.styleable.CounterView_titleText,R.string.default_title))

            counterTitle?.text = title

            typedArray.recycle()
        }
    }


    fun initializeCounter (title : String?, expireDate: String?){

        counterTitle?.text = title

        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX")
        try {
            endDate.time = formatter.parse(expireDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }

        val start_millis = startDate.getTimeInMillis()
        val end_millis = endDate.getTimeInMillis()
        val total_millis = start_millis - end_millis

        val cdt = object : CountDownTimer(total_millis, 1) {
            override fun onTick(millisUntilFinished: Long) {

                var millisUntilFinished = millisUntilFinished
                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)

                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

                counterTime?.text = resources.getString(R.string.time_text, days?.toString(),hours?.toString(), minutes?.toString(),seconds?.toString())

            }

            override fun onFinish() {
                counterTime?.text = resources.getString(R.string.campaign_end)
            }
        }
        cdt.start()
    }

}