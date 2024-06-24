package com.example.wattravl

import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.widget.Button
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.example.wattravl.viewmodel.ViewModel
import java.util.logging.Level
import java.util.logging.Logger

private val logger = Logger.getLogger("MapActivity")

class MapActivity : AppCompatActivity() {
    // private val viewModel = ViewModel(this)
    private lateinit var scaleDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private var scale = 10f
    private var curOffsetX = 0f
    private var curOffsetY = 0f
    private lateinit var viewModel: ViewModel
    private lateinit var imgView: ImageView

    var densityScale = 0f // used to convert screen distances to pixels

    val matrix = Matrix()

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateImage() {
        val bounds = windowManager.currentWindowMetrics.bounds
        val width = bounds.width()
        val height = bounds.height()

        val bitmap = viewModel.draw(scale, curOffsetX, curOffsetY, width, height)
        imgView.setImageBitmap(bitmap)
    }


    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_map)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Retrieve the parameters from the Intent
        val selectedFromLocation = intent.getStringExtra("SELECTED_FROM_LOCATION")
        val selectedToLocation = intent.getStringExtra("SELECTED_TO_LOCATION")
        val selectedFromRoom = intent.getStringExtra("SELECTED_FROM_ROOM")
        val selectedToRoom = intent.getStringExtra("SELECTED_TO_ROOM")

        val btn: Button = findViewById(R.id.button2)
        btn.text = "PUT MAP HERE:\n" + "From Location: $selectedFromLocation Room $selectedFromRoom\nTo Location: $selectedToLocation Room $selectedToRoom"
        btn.isVisible = false

        densityScale = applicationContext.resources.displayMetrics.density - 0.55f

        imgView = findViewById(R.id.imageView)

        viewModel = ViewModel(this)
        viewModel.drawPath()
        updateImage()

        scaleDetector = ScaleGestureDetector(applicationContext, object: OnScaleGestureListener {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                // logger.log(Level.INFO, "On scale called")
                scale *= detector.scaleFactor
                matrix.postScale(detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY)
                return true // event handled
            }

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                // logger.log(Level.INFO, "On scale begin")
                return true
            }

            override fun onScaleEnd(p0: ScaleGestureDetector) {
            }
        })

        gestureDetector = GestureDetector(applicationContext, object: GestureDetector.OnGestureListener {
            override fun onScroll(
                p0: MotionEvent?,
                p1: MotionEvent,
                p2: Float,
                p3: Float
            ): Boolean {
                // Implement movement code here
                // logger.log(Level.INFO, "On scroll called")
                curOffsetX += p2 * densityScale / scale
                curOffsetY += p3 * densityScale / scale
                matrix.postTranslate(p2 * densityScale / scale, p3 * densityScale / scale)
                return true
            }

            override fun onDown(p0: MotionEvent): Boolean {
                return false;
            }

            override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
                return false;
            }

            override fun onLongPress(p0: MotionEvent) {

            }

            override fun onShowPress(p0: MotionEvent) {

            }

            override fun onSingleTapUp(p0: MotionEvent): Boolean {
                return false;
            }
        })

        imgView.setOnTouchListener { view, motionEvent ->
            // logger.log(Level.INFO, "Touch listener")
            scaleDetector.onTouchEvent(motionEvent)
            gestureDetector.onTouchEvent(motionEvent)
            updateImage()

            // logger.log(Level.INFO, "Touched at " + motionEvent.x + " " + motionEvent.y)
            true
        }
    }
}