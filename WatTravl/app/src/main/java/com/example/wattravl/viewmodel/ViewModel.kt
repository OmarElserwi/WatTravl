package com.example.wattravl.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.RectF
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.caverock.androidsvg.RenderOptions
import com.caverock.androidsvg.SVG
import com.example.wattravl.MapActivity
import com.example.wattravl.model.*
import java.util.logging.Level
import java.util.logging.Logger

private val logger = Logger.getLogger("ViewModel")

class ViewModel(
    private val activity: MapActivity,
) {
    var resBitmap: MutableLiveData<Bitmap>? = null
    private var svg: SVG = SVG.getFromInputStream(activity.assets.open("MCFloor1.svg"))

    @RequiresApi(Build.VERSION_CODES.N)
    fun logPath() {
        val hallways = buildGraph()

        // Create the classroom to hallway map
        val classroomToHallwayMap = createClassroomToHallwayMap(hallways)

        // Change these to the desired starting and ending classroom IDs
        val startClassroomId = 1027
        val endClassroomId = 1085

        // Find the corresponding hallway nodes for the start and end classrooms
        val startNodeId = classroomToHallwayMap[startClassroomId] ?: throw IllegalArgumentException("Classroom $startClassroomId does not exist in the map")
        val endNodeId = classroomToHallwayMap[endClassroomId] ?: throw IllegalArgumentException("Classroom $endClassroomId does not exist in the map")

        val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
        val endNode = hallways[endNodeId] ?: throw IllegalArgumentException("Hallway node $endNodeId does not exist")

        val distances = dijkstra(startNode)

        val path = getPath(distances, startNode, endNode)
        logger.log(Level.INFO, path.joinToString(" -> ") { it.nodeId.toString() })
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun draw(zoomScale: Float, offsetX: Float, offsetY: Float, width: Int, height: Int): Bitmap {
        var bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        var canvas = Canvas(bitmap)

        // logger.log(Level.INFO, "Width = " + width + " Height = " + height);
        logger.log(Level.INFO, "Scale = " + zoomScale + " offsetX = " + offsetX + " offsetY = " + offsetY)
        svg.setDocumentViewBox(offsetX, offsetY, width.toFloat() / (zoomScale * 10), height.toFloat() / (zoomScale * 10))
        svg.renderToCanvas(canvas)

        logPath()

        return bitmap

        // svg.renderToPicture()
    }

    fun drawPath() {
        TODO("not implemented")
    }
}