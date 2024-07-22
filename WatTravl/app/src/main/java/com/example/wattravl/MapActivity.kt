package com.example.wattravl

import android.content.Intent
import android.graphics.Matrix
import android.os.Build
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.ScaleGestureDetector.OnScaleGestureListener
import android.view.View
import android.widget.AdapterView
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.wattravl.model.MC.Model
import com.example.wattravl.viewmodel.ViewModel
import java.lang.Exception
import java.util.logging.Level
import java.util.logging.Logger
import kotlin.math.floor

private val logger = Logger.getLogger("MapActivity")

class MapActivity : AppCompatActivity() {
    private lateinit var scaleDetector: ScaleGestureDetector
    private lateinit var gestureDetector: GestureDetector
    private var scale = 10f
    private var curOffsetX = 700f
    private var curOffsetY = 200f
    private lateinit var viewModel: ViewModel
    private lateinit var imgView: ImageView
    private lateinit var buildingSpinner: Spinner
    private lateinit var floorSpinner: Spinner

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
        val isAccessability = intent.getBooleanExtra("SELECTED_ACCESS", false)
        val isIndoors = intent.getBooleanExtra("SELECTED_INDOOR", false)


        val backButton: ImageButton = findViewById(R.id.imageButton)
        backButton.setOnClickListener {
            // Start MainActivity and pass the login state
            val intent = Intent(this, MainActivity::class.java).apply {
                putExtra("isloggedin", true)
            }
            startActivity(intent)
            finish()
        }

        val relaunchButton: Button = findViewById(R.id.relaunchButton)
        relaunchButton.setOnClickListener {
            val buildingSpinner: Spinner = findViewById(R.id.buildingSpinner)
            val floorSpinner: Spinner = findViewById(R.id.floorSpinner)
            val building = buildingSpinner.selectedItem.toString()
            val floor = floorSpinner.selectedItem.toString()

            // Put logic here for update floor
            viewModel.updateFloor(floor.last().digitToInt())
            updateImage()
        }

        buildingSpinner = findViewById(R.id.buildingSpinner)
        floorSpinner = findViewById(R.id.floorSpinner)

        // Set up spinners
        val buildingAdapter = ArrayAdapter.createFromResource(this, R.array.Locations, android.R.layout.simple_spinner_item)
        buildingAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        buildingSpinner.adapter = buildingAdapter

        val floorAdapter = ArrayAdapter.createFromResource(this, R.array.Floors, android.R.layout.simple_spinner_item)
        floorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        floorSpinner.adapter = floorAdapter
        floorSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val floor = floorSpinner.selectedItem.toString()

                viewModel.updateFloor(floor.last().digitToInt())
                updateImage()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                // do nothing
            }
        }

        densityScale = applicationContext.resources.displayMetrics.density - 0.55f

        imgView = findViewById(R.id.imageView)

        viewModel = ViewModel(this)

        val fromRoom = Model.convertCharRooms(selectedFromRoom!!)
        val toRoom = Model.convertCharRooms(selectedToRoom!!)
        logger.log(Level.INFO, isAccessability.toString())
        floorSpinner.setSelection(selectedFromRoom.toString().first().digitToInt() - 1)
        /*
        val coords = getNodeCoords(model.getNodeId(fromRoom))
        curOffsetX = coords.first.toFloat() * densityScale / scale
        curOffsetY = coords.second.toFloat() * densityScale / scale

         */
        viewModel.drawPath(fromRoom, toRoom, isAccessability)

        updateImage()

        scaleDetector = ScaleGestureDetector(applicationContext, object : OnScaleGestureListener {
            override fun onScale(detector: ScaleGestureDetector): Boolean {
                scale *= detector.scaleFactor
                matrix.postScale(detector.scaleFactor, detector.scaleFactor, detector.focusX, detector.focusY)
                return true // event handled
            }

            override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
                return true
            }

            override fun onScaleEnd(p0: ScaleGestureDetector) {
            }
        })

        gestureDetector = GestureDetector(applicationContext, object : GestureDetector.OnGestureListener {
            override fun onScroll(
                p0: MotionEvent?,
                p1: MotionEvent,
                p2: Float,
                p3: Float
            ): Boolean {
                curOffsetX += p2 * densityScale / scale
                curOffsetY += p3 * densityScale / scale
                matrix.postTranslate(p2 * densityScale / scale, p3 * densityScale / scale)
                return true
            }

            override fun onDown(p0: MotionEvent): Boolean {
                return false
            }

            override fun onFling(p0: MotionEvent?, p1: MotionEvent, p2: Float, p3: Float): Boolean {
                return false
            }

            override fun onLongPress(p0: MotionEvent) {
            }

            override fun onShowPress(p0: MotionEvent) {
            }

            override fun onSingleTapUp(p0: MotionEvent): Boolean {
                return false
            }
        })

        imgView.setOnTouchListener { view, motionEvent ->
            scaleDetector.onTouchEvent(motionEvent)
            gestureDetector.onTouchEvent(motionEvent)
            updateImage()
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
            201 to Pair(434, 482), // floor 2 MC
            202 to Pair(473, 482),
            203 to Pair(494, 197),
            204 to Pair(494, 107),
            205 to Pair(518, 482),
            206 to Pair(518, 437),
            207 to Pair(518, 383),
            208 to Pair(516, 347),
            209 to Pair(517, 299),
            210 to Pair(516, 239),
            211 to Pair(516, 215),
            212 to Pair(515, 197),
            213 to Pair(572, 484),
            214 to Pair(574, 197),
            215 to Pair(616, 481),
            216 to Pair(616, 196),
            217 to Pair(671, 481),
            218 to Pair(671, 436),
            219 to Pair(672, 351),
            220 to Pair(671, 304),
            221 to Pair(673, 238),
            222 to Pair(671, 197),
            223 to Pair(699, 482),
            224 to Pair(704, 197),
            225 to Pair(749, 483),
            226 to Pair(732, 452),
            227 to Pair(749, 197),
            228 to Pair(809, 482),
            229 to Pair(797, 196),
            230 to Pair(848, 482),
            231 to Pair(848, 450),
            232 to Pair(833, 195),
            233 to Pair(872, 557),
            234 to Pair(870, 503),
            235 to Pair(869, 481),
            236 to Pair(899, 480),
            237 to Pair(898, 451),
            238 to Pair(895, 410),
            239 to Pair(893, 363),
            240 to Pair(893, 315),
            241 to Pair(893, 267),
            242 to Pair(894, 223),
            243 to Pair(892, 197),
            301 to Pair(433, 449), // MC floor 3
            302 to Pair(467, 435),
            303 to Pair(467, 367),
            304 to Pair(467, 277),
            305 to Pair(467, 190),
            306 to Pair(467, 166),
            307 to Pair(466, 103),
            308 to Pair(488, 276),
            309 to Pair(503, 190),
            310 to Pair(563, 434),
            311 to Pair(568, 277),
            312 to Pair(547, 192),
            313 to Pair(590, 192),
            314 to Pair(658, 433),
            315 to Pair(659, 274),
            316 to Pair(635, 192),
            317 to Pair(702, 275),
            318 to Pair(681, 190),
            319 to Pair(747, 434),
            320 to Pair(746, 276),
            321 to Pair(725, 190),
            322 to Pair(767, 190),
            323 to Pair(779, 277),
            324 to Pair(779, 208),
            325 to Pair(803, 276),
            326 to Pair(814, 208),
            327 to Pair(848, 550),
            328 to Pair(848, 500),
            329 to Pair(845, 466),
            330 to Pair(865, 461),
            331 to Pair(860, 433),
            332 to Pair(857, 355),
            333 to Pair(860, 300),
            334 to Pair(858, 267),
            335 to Pair(857, 206),
            400 to Pair(403, 473), // MC Floor 4
            401 to Pair(443, 495),
            402 to Pair(443, 471),
            403 to Pair(440, 381),
            404 to Pair(442, 345),
            405 to Pair(442, 313),
            406 to Pair(442, 294),
            407 to Pair(442, 261),
            408 to Pair(443, 204),
            409 to Pair(442, 185),
            410 to Pair(442, 114),
            411 to Pair(479, 495),
            412 to Pair(471, 184),
            413 to Pair(501, 495),
            414 to Pair(500, 185),
            415 to Pair(536, 495),
            416 to Pair(548, 185),
            417 to Pair(584, 495),
            418 to Pair(584, 182),
            419 to Pair(617, 495),
            420 to Pair(617, 183),
            421 to Pair(641, 496),
            422 to Pair(639, 463),
            423 to Pair(641, 400),
            424 to Pair(640, 365),
            425 to Pair(639, 321),
            426 to Pair(640, 276),
            427 to Pair(640, 199),
            428 to Pair(638, 181),
            429 to Pair(671, 496),
            430 to Pair(672, 182),
            431 to Pair(716, 497),
            432 to Pair(716, 182),
            433 to Pair(757, 496),
            434 to Pair(760, 182),
            435 to Pair(784, 496),
            436 to Pair(793, 220),
            437 to Pair(791, 182),
            438 to Pair(817, 562),
            439 to Pair(815, 496),
            440 to Pair(815, 479),
            441 to Pair(817, 422),
            442 to Pair(816, 359),
            443 to Pair(815, 311),
            444 to Pair(814, 284),
            445 to Pair(814, 237),
            446 to Pair(813, 197),
            447 to Pair(853, 455),
            448 to Pair(848, 195),
            449 to Pair(882, 456),
            450 to Pair(913, 456),
            451 to Pair(902, 357),
            452 to Pair(903, 251),
            453 to Pair(900, 196),
            454 to Pair(947, 456),
            455 to Pair(964, 456),
            501 to Pair(422, 484), // MC Floor 5
            502 to Pair(421, 454),
            503 to Pair(422, 397),
            504 to Pair(421, 352),
            505 to Pair(420, 308),
            506 to Pair(422, 264),
            507 to Pair(423, 210),
            508 to Pair(422, 172),
            509 to Pair(475, 487),
            510 to Pair(474, 453),
            511 to Pair(478, 210),
            512 to Pair(476, 170),
            513 to Pair(476, 147),
            514 to Pair(476, 99),
            515 to Pair(503, 510),
            516 to Pair(513, 452),
            517 to Pair(512, 209),
            518 to Pair(512, 147),
            519 to Pair(535, 514),
            520 to Pair(557, 514),
            521 to Pair(557, 451),
            522 to Pair(557, 209),
            523 to Pair(557, 148),
            524 to Pair(578, 513),
            525 to Pair(600, 513),
            526 to Pair(592, 449),
            527 to Pair(590, 360),
            528 to Pair(590, 330),
            529 to Pair(587, 299),
            530 to Pair(590, 210),
            531 to Pair(602, 147),
            532 to Pair(622, 514),
            533 to Pair(644, 513),
            534 to Pair(645, 451),
            535 to Pair(644, 209),
            536 to Pair(643, 147),
            537 to Pair(667, 514),
            538 to Pair(688, 514),
            539 to Pair(681, 451),
            540 to Pair(688, 210),
            541 to Pair(688, 148),
            542 to Pair(711, 514),
            543 to Pair(710, 450),
            544 to Pair(711, 211),
            545 to Pair(732, 513),
            546 to Pair(733, 452),
            547 to Pair(732, 211),
            548 to Pair(732, 146),
            549 to Pair(755, 452),
            550 to Pair(754, 210),
            551 to Pair(775, 515),
            552 to Pair(775, 452),
            553 to Pair(777, 212),
            554 to Pair(775, 147),
            555 to Pair(799, 514),
            556 to Pair(797, 452),
            557 to Pair(797, 210),
            558 to Pair(797, 147),
            559 to Pair(827, 514),
            560 to Pair(826, 452),
            561 to Pair(826, 211),
            562 to Pair(826, 148),
            563 to Pair(854, 557),
            564 to Pair(853, 515),
            565 to Pair(866, 466),
            566 to Pair(877, 397),
            567 to Pair(877, 354),
            568 to Pair(876, 307),
            569 to Pair(877, 251),
            570 to Pair(876, 190),
            571 to Pair(1123, 489),
            572 to Pair(910, 250),
            573 to Pair(909, 189),
            574 to Pair(931, 471),
            575 to Pair(932, 355),
            576 to Pair(935, 249),
            577 to Pair(929, 190),
            578 to Pair(953, 250),
            579 to Pair(981, 471),
            580 to Pair(980, 421),
            581 to Pair(981, 395),
            582 to Pair(980, 374),
            583 to Pair(980, 353),
            584 to Pair(979, 331),
            585 to Pair(980, 287),
            586 to Pair(979, 250),
            587 to Pair(980, 191),
            2010 to Pair(508, 381), // DC Floor 2
            2020 to Pair(546, 381),
            2030 to Pair(570, 381),
            2040 to Pair(595, 381),
            2050 to Pair(621, 381),
            2060 to Pair(648, 379),
            2070 to Pair(673, 380),
            2080 to Pair(686, 380),
            2090 to Pair(700, 381),
            2100 to Pair(725, 380),
            2110 to Pair(745, 379),
            2120 to Pair(765, 380),
            2130 to Pair(507, 407),
            2140 to Pair(508, 441),
            2150 to Pair(576, 441),
            2160 to Pair(642, 441),
            2170 to Pair(661, 441),
            2180 to Pair(688, 442),
            2190 to Pair(737, 442),
            2200 to Pair(812, 442),
            2210 to Pair(849, 441),
            2220 to Pair(892, 441),
            2230 to Pair(918, 441),
            2240 to Pair(979, 444),
            2250 to Pair(1022, 442),
            2260 to Pair(1085, 441),
            2270 to Pair(1086, 403),
            2280 to Pair(968, 288),
            2290 to Pair(1086, 385),
            2300 to Pair(1086, 358),
            2310 to Pair(1086, 333),
            2320 to Pair(1086, 307),
            2330 to Pair(1086, 281),
            2340 to Pair(1086, 254),
            2350 to Pair(1086, 228),
            2360 to Pair(1086, 199),
            2370 to Pair(1086, 180),
            2380 to Pair(1086, 159),
            2390 to Pair(1061, 157),
            2400 to Pair(1062, 105),
            2410 to Pair(575, 492),
            2420 to Pair(576, 553),
            2430 to Pair(575, 582),
            2440 to Pair(661, 463),
            2450 to Pair(660, 521),
            2460 to Pair(661, 582),
            2470 to Pair(736, 483),
            2480 to Pair(737, 561),
            2490 to Pair(813, 483),
            2500 to Pair(813, 521),
            2510 to Pair(813, 558),
            2520 to Pair(979, 468),
            2530 to Pair(978, 544),
            2540 to Pair(1086, 477),
            2550 to Pair(1086, 511),
            2560 to Pair(1085, 535),
            2570 to Pair(1085, 557),
            2580 to Pair(505, 608),
            2590 to Pair(575, 606),
            2600 to Pair(662, 603),
            2610 to Pair(686, 603),
            2620 to Pair(737, 604),
            2630 to Pair(811, 604),
            2640 to Pair(849, 602),
            2650 to Pair(891, 602),
            2660 to Pair(979, 601),
            2670 to Pair(1021, 602),
            2680 to Pair(1083, 602),
            2690 to Pair(1086, 642),
            2700 to Pair(1086, 662),
            2710 to Pair(1086, 685),
            2720 to Pair(1086, 713),
            2730 to Pair(1086, 736),
            2740 to Pair(507, 662),
            2750 to Pair(543, 663),
            2760 to Pair(569, 664),
            2770 to Pair(596, 662),
            2780 to Pair(622, 663),
            2790 to Pair(648, 663),
            2800 to Pair(673, 663),
            2810 to Pair(699, 663),
            2820 to Pair(725, 663),
            2830 to Pair(746, 663),
            2840 to Pair(241, 551),
            2850 to Pair(263, 551),
            2860 to Pair(300, 551),
            2870 to Pair(332, 552),
            2880 to Pair(220, 617),
            2890 to Pair(243, 617),
            2900 to Pair(333, 615),
            2910 to Pair(351, 605),
            2920 to Pair(350, 661),
            2930 to Pair(228, 662),
            2940 to Pair(267, 662),
            2950 to Pair(294, 663),
            2960 to Pair(319, 662),
            1010 to Pair(899, 416), //  DC Floor 1
            1020 to Pair(932, 416),
            1030 to Pair(1028, 415),
            1040 to Pair(1067, 414),
            1050 to Pair(1066, 377),
            1060 to Pair(1065, 352),
            1070 to Pair(1067, 315),
            1080 to Pair(1066, 242),
            1090 to Pair(1065, 190),
            1100 to Pair(1066, 139),
            1110 to Pair(1066, 86),
            1120 to Pair(1095, 416),
            1130 to Pair(1095, 455),
            1140 to Pair(1095, 483),
            1150 to Pair(1096, 512),
            1160 to Pair(1095, 542),
            1170 to Pair(1096, 575),
            1180 to Pair(1095, 598),
            1190 to Pair(1096, 638),
            1200 to Pair(1096, 664),
            1210 to Pair(1096, 684),
            1220 to Pair(1096, 716),
            1230 to Pair(900, 444),
            1240 to Pair(873, 444),
            1250 to Pair(874, 566),
            1260 to Pair(874, 593),
            1270 to Pair(934, 592),
            1280 to Pair(994, 594),
            1290 to Pair(1026, 594),
            1300 to Pair(1065, 594),
            1310 to Pair(1065, 532),
            1320 to Pair(1065, 483),
            1330 to Pair(1065, 437),
            1340 to Pair(1028, 432)
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
