package com.example.graph
import com.example.graph.*
import com.example.graph.floor1.*
import com.example.graph.Dijkstra.*

fun main() {
    // Build the graph
    val hallways = buildGraph()

    // Run Dijkstra's algorithm from hallway1
    val startNode = hallways.first()
    val distances = dijkstra(startNode)

    // Print the distances
    distances.forEach { (hallway, distance) ->
        println("Distance from Hallway ${startNode.nodeId} to Hallway ${hallway.nodeId}: $distance")
    }
}
