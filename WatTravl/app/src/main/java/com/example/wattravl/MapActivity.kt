package com.example.wattravl

import android.content.Intent
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
import com.example.wattravl.model.MC.Model
import com.example.wattravl.viewmodel.ViewModel
import java.lang.Exception
import java.util.logging.Logger

private val logger = Logger.getLogger("MapActivity")

class MapActivity : AppCompatActivity() {
    // private val viewModel = ViewModel(this)
    private lateinit var scaleDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private var scale = 10f
    private var curOffsetX = 500f
    private var curOffsetY = 200f
    private lateinit var viewModel: ViewModel
    private lateinit var imgView: ImageView

    var densityScale = 0f // used to convert screen distances to pixels

    val matrix = Matrix()

    private val model = Model()

    @RequiresApi(Build.VERSION_CODES.R)
    fun updateImage() {
        val bounds = windowManager.currentWindowMetrics.bounds
        val width = bounds.width()
        val height = bounds.height()

        val bitmap = viewModel.draw(scale, curOffsetX, curOffsetY, width, height)
        imgView.setImageBitmap(bitmap)
    }

    fun convertCharRooms(id: String): Int {
        if (id.length == 5) {
            val lastDigit = id[4] - 'A' + 1
            return id.substring(0, 4).toInt() * 10 + lastDigit
        } else {
            return id.toInt()
        }
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

        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            // Start SecondActivity and pass the selected items
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        //val btn: Button = findViewById(R.id.button2)
        //btn.text = "PUT MAP HERE:\n" + "From Location: $selectedFromLocation Room $selectedFromRoom\nTo Location: $selectedToLocation Room $selectedToRoom"
        //btn.isVisible = false

        densityScale = applicationContext.resources.displayMetrics.density - 0.55f

        imgView = findViewById(R.id.imageView)

        viewModel = ViewModel(this)

        val fromRoom = convertCharRooms(selectedFromRoom!!)
        val toRoom = convertCharRooms(selectedToRoom!!)
        val coords = getNodeCoords(model.getNodeId(fromRoom))
        curOffsetX = coords.first.toFloat() * densityScale / scale
        curOffsetY = coords.second.toFloat() * densityScale / scale
        viewModel.drawPath(fromRoom, toRoom)

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

    companion object {
        val nodesToCoords = mapOf(
            101 to Pair(600, 640),
            102 to Pair(600, 595),
            103 to Pair(600, 539),
            104 to Pair(600, 474),
            105 to Pair(600, 405),
            106 to Pair(600, 362),
            107 to Pair(600, 305),
            108 to Pair(600, 260),
            109 to Pair(665, 640),
            110 to Pair(665, 620),
            111 to Pair(665, 580),
            112 to Pair(665, 520),
            113 to Pair(665, 407),
            114 to Pair(665, 260),
            115 to Pair(665, 167),
            116 to Pair(710, 640),
            118 to Pair(765, 640),
            120 to Pair(825, 640),
            122 to Pair(900, 640),
            117 to Pair(715, 260),
            119 to Pair(760, 260),
            121 to Pair(825, 260),
            123 to Pair(900, 545),
            124 to Pair(900, 435),
            125 to Pair(900, 360),
            126 to Pair(900, 260),
            127 to Pair(940, 640),
            130 to Pair(1000, 640),
            134 to Pair(1060, 640),
            137 to Pair(1110, 640),
            139 to Pair(1140, 640),
            143 to Pair(1165, 640),
            145 to Pair(1200, 640),
        )

        val nodesToCoords2 = mapOf(
            146 to Pair(900, 430),
            147 to Pair(900, 408),
            148 to Pair(900, 345),
            149 to Pair(900, 320),
            150 to Pair(900, 285),
            151 to Pair(900, 195),
            129 to Pair(705, 195),
            133 to Pair(750, 195),
            141 to Pair(850, 195),
            136 to Pair(795, 195),
            128 to Pair(725, 408),
            131 to Pair(757, 408),
            132 to Pair(757, 389),
            135 to Pair(795, 408),
            138 to Pair(825, 408),
            140 to Pair(840, 408),
            144 to Pair(866, 408),
            142 to Pair(873, 565),
            152 to Pair(940, 452),
            153 to Pair(970, 452),
        )

        fun getNodeCoords(id: Int): Pair<Int, Int> {
            if (nodesToCoords.containsKey(id)) {
                val coords = nodesToCoords[id]!!
                return Pair((coords.first * 1224 / 1632), (coords.second * 792 / 1056))
            } else if (nodesToCoords2.containsKey(id)) {
                return nodesToCoords2[id]!!
            } else {
                throw Exception("Invalid node ID " + id)
            }
        }
    }
}