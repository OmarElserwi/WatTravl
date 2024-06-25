package com.example.wattravl.model
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
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
    distances.forEach { (hallway, distance) ->
        println("Distance from ${startNode.nodeId} to ${hallway.nodeId}: ${distance.first}")
    }

    printPath(distances, startNode, endNode)
}
