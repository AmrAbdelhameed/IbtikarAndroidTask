package com.example.ibtikarandroidtask.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import android.widget.Toast
import com.artjimlop.altex.AltexImageDownloader
import com.example.ibtikarandroidtask.R

class ImageOverlayView : RelativeLayout {
    lateinit var imageUrl: String

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        val view = inflate(context, R.layout.view_image_overlay, this)
        view.findViewById<View>(R.id.save).setOnClickListener {
            AltexImageDownloader.writeToDisk(
                context,
                imageUrl,
                System.currentTimeMillis().toString()
            );
            Toast.makeText(context, R.string.saved, Toast.LENGTH_LONG).show()
        }
    }
}