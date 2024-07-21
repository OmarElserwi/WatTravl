package com.example.wattravl.model.MC

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.wattravl.model.HallwayNode
import com.example.wattravl.model.buildGraph
import com.example.wattravl.model.createClassroomToHallwayMap
import com.example.wattravl.model.dijkstra
import com.example.wattravl.model.printPath

class Model {
    private val hallways = buildGraph()
    private val classroomToHallwayMap = createClassroomToHallwayMap(hallways)

    @RequiresApi(Build.VERSION_CODES.N)
    fun getPath(start: Int, end: Int): List<HallwayNode> {
        val startClassroomId = start
        val endClassroomId = end

        // Find the corresponding hallway nodes for the start and end classrooms
        val startNodeId = classroomToHallwayMap[startClassroomId] ?: throw IllegalArgumentException("Classroom $startClassroomId does not exist in the map")
        val endNodeId = classroomToHallwayMap[endClassroomId] ?: throw IllegalArgumentException("Classroom $endClassroomId does not exist in the map")

        val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
        val endNode = hallways[endNodeId] ?: throw IllegalArgumentException("Hallway node $endNodeId does not exist")

        val distances = dijkstra(startNode)
        /*
        distances.forEach { (hallway, distance) ->
            println("Distance from ${startNode.nodeId} to ${hallway.nodeId}: ${distance.first}")
        }

         */

        return printPath(distances, startNode, endNode)
    }

    fun getNodeId(roomId: Int): Int {
        return classroomToHallwayMap[roomId] ?: throw IllegalArgumentException("Classroom $roomId does not exist in the map")
    }
}