package com.example.model.MC

import com.example.model.MC.*

fun main() {
    // Build the graph
    val hallways = buildGraph()

    // Run Dijkstra's algorithm from hallway1
    val startNode = hallways.values.first()
    val distances = dijkstra(startNode)

    // Print the distances
    distances.forEach { (hallway, distance) ->
        println("Distance from Hallway ${startNode.nodeId} to Hallway ${hallway.nodeId}: $distance")
    }
}
