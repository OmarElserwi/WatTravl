package com.example.model.MC

fun main() {
    val hallways = buildGraph()
    
    // Change this to the desired starting node
    val startNodeId = 123
    val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
    
    val distances = dijkstra(startNode)
    distances.forEach { (hallway, distance) ->
        println("Distance from ${startNode.nodeId} to ${hallway.nodeId}: $distance")
    }
}
