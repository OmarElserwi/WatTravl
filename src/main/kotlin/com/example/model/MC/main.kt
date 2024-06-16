package com.example.model.MC

fun main() {
    val hallways = buildGraph()

    // Change these to the desired starting and ending nodes
    val startNodeId = 101
    val endNodeId = 153

    val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
    val endNode = hallways[endNodeId] ?: throw IllegalArgumentException("Hallway node $endNodeId does not exist")

    val distances = dijkstra(startNode)
    distances.forEach { (hallway, distance) ->
        println("Distance from ${startNode.nodeId} to ${hallway.nodeId}: ${distance.first}")
    }

    printPath(distances, startNode, endNode)
}
