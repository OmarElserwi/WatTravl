package com.example.wattravl.viewmodel

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.RectF
import android.os.Build
import android.view.Display.Mode
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import com.caverock.androidsvg.RenderOptions
import com.caverock.androidsvg.SVG
import com.example.wattravl.MapActivity
import com.example.wattravl.model.*
import com.example.wattravl.model.MC.Model
import java.util.logging.Level
import java.util.logging.Logger

private val logger = Logger.getLogger("ViewModel")

class ViewModel(
    private val activity: MapActivity,
) {
    private val model = Model()
    var resBitmap: MutableLiveData<Bitmap>? = null
    private var svg: SVG = SVG.getFromInputStream(activity.assets.open("MCFloor1.svg"))
    val pathCoordinates: MutableList<Pair<Int, Int>> = mutableListOf()

    @RequiresApi(Build.VERSION_CODES.R)
    private fun transform(coord: Pair<Int, Int>, zoomScale: Float, offsetX: Float, offsetY: Float): Pair<Float, Float> {
        val x = coord.first.toFloat()
        val y = coord.second.toFloat()
        // val newX = (x - offsetX) * (zoomScale * 10)
        // val newY = (y - offsetY) * (zoomScale * 10)

        /*
        var newX = x - offsetX
        var newY = y - offsetY
        newX /= activity.densityScale
        newY /= activity.densityScale
        newX *= zoomScale
        newY *= zoomScale
        return Pair(newX, newY) // so that when we render it's in screen coordinates, not pixels

         */


        val invertedMatrix = Matrix()
        activity.matrix.invert(invertedMatrix)

        val tmpCoords = floatArrayOf(x, y);
        invertedMatrix.mapPoints(tmpCoords)

        var newX = tmpCoords[0]
        var newY = tmpCoords[1]
        /*
        newX /= activity.densityScale
        newY /= activity.densityScale
        newX *= zoomScale
        newY *= zoomScale

         */

        return Pair(newX, newY)

    }

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
        // logger.log(Level.INFO, "Scale = " + zoomScale + " offsetX = " + offsetX + " offsetY = " + offsetY)
        // logger.log(Level.INFO, svg.renderDPI.toString())
        // logger.log(Level.INFO, svg.documentWidth.toString() + " " + svg.documentHeight.toString())
        // so what's da scale that I move exactly 1 pixel on this SVG?

        // offsetX offsetY is rendered at some random ass point wtf?
        svg.setDocumentViewBox(offsetX, offsetY, width.toFloat() / zoomScale, height.toFloat() / zoomScale)
        // svg.setDocumentViewBox(offsetX, offsetY, width.toFloat(), height.toFloat())
        svg.renderToCanvas(canvas)

        var svgString = """
            <svg xmlns="http://www.w3.org/2000/svg" width="1632" height="1056">
        """.trimIndent()

        pathCoordinates.zipWithNext().forEach { (coord1, coord2) ->
            svgString += "<line x1=\"" + coord1.first + "\" y1 = \"" + coord1.second + "\" x2 = \"" + coord2.first + "\" y2 = \"" + coord2.second + "\" stroke = \"blue\" stroke-width = \"2\"/>"
        }

        svgString += "</svg>"
        svgString.trimIndent()

        val svg2 = SVG.getFromString(svgString)
        svg2.setDocumentViewBox(offsetX, offsetY, width.toFloat() / zoomScale, height.toFloat() / zoomScale)
        svg2.renderToCanvas(canvas)

        return bitmap

        // svg.renderToPicture()
    }

    fun setPathCoordinates(pathCoords: List<Pair<Int, Int>>) {
        pathCoordinates.clear()
        pathCoordinates.addAll(pathCoords)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun drawPath(start: Int, end: Int) {
        val path = model.getPath(start, end)
        path.forEach {
            logger.log(Level.INFO, it.nodeId.toString())
        }

        setPathCoordinates(listOf(
            Pair(300, 200),
            Pair(500, 1000),
            Pair(200, 700),
            Pair(1000, 200)
        ))
    }
}