package com.example.wattravl.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import androidx.lifecycle.MutableLiveData
import com.caverock.androidsvg.RenderOptions
import com.caverock.androidsvg.SVG
import com.example.wattravl.MapActivity
import java.util.logging.Level
import java.util.logging.Logger

private val logger = Logger.getLogger("ViewModel")

class ViewModel(
    private val activity: MapActivity,
) {
    var resBitmap: MutableLiveData<Bitmap>? = null
    private var svg: SVG = SVG.getFromInputStream(activity.assets.open("MCFloor1.svg"))

    fun draw(zoomScale: Float, offsetX: Float, offsetY: Float, width: Int, height: Int): Bitmap {
        var bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)

        // logger.log(Level.INFO, "Width = " + width + " Height = " + height);
        logger.log(Level.INFO, "Scale = " + zoomScale + " offsetX = " + offsetX + " offsetY = " + offsetY)
        svg.setDocumentViewBox(offsetX, offsetY, width.toFloat() / (zoomScale * 10), height.toFloat() / (zoomScale * 10))
        svg.renderToCanvas(canvas)

        return bitmap

        // svg.renderToPicture()
    }

    fun drawPath() {
        TODO("not implemented")
    }
}