package com.example.model.MC

import java.util.PriorityQueue

fun dijkstra(start: HallwayNode): Map<HallwayNode, Pair<Double, HallwayNode?>> {
    val distances = mutableMapOf<HallwayNode, Pair<Double, HallwayNode?>>().withDefault { Double.MAX_VALUE to null }
    val priorityQueue = PriorityQueue<Pair<HallwayNode, Double>>(compareBy { it.second })

    distances[start] = 0.0 to null
    priorityQueue.add(start to 0.0)

    while (priorityQueue.isNotEmpty()) {
        val (currentNode, currentDistance) = priorityQueue.poll()

        if (currentDistance > distances.getValue(currentNode).first) continue

        listOfNotNull(currentNode.north, currentNode.south, currentNode.east, currentNode.west).forEach { neighbor ->
            val distance = globalDistances[currentNode to neighbor] ?: return@forEach
            val newDistance = currentDistance + distance
            if (newDistance < distances.getValue(neighbor).first) {
                distances[neighbor] = newDistance to currentNode
                priorityQueue.add(neighbor to newDistance)
            }
        }
    }

    return distances
}

fun printPath(distances: Map<HallwayNode, Pair<Double, HallwayNode?>>, start: HallwayNode, end: HallwayNode) {
    val path = mutableListOf<HallwayNode>()
    val pathDistances = mutableListOf<Double>()
    val orientations = mutableListOf<String>()
    var currentNode: HallwayNode? = end

    while (currentNode != null) {
        path.add(currentNode)
        val previousNode = distances[currentNode]?.second
        if (previousNode != null) {
            pathDistances.add(globalDistances[previousNode to currentNode] ?: 0.0)
            val orientation = when (currentNode) {
                previousNode.north -> "north"
                previousNode.south -> "south"
                previousNode.east -> "east"
                previousNode.west -> "west"
                else -> "unknown"
            }
            orientations.add(orientation)
        }
        currentNode = previousNode
    }

    path.reverse()
    pathDistances.reverse()
    orientations.reverse()

    println("Path from ${start.nodeId} to ${end.nodeId}:")

    var currentDirection = orientations.firstOrNull()
    var currentDistance = 0.0

    path.forEachIndexed { index, node ->
        if (index < pathDistances.size) {
            if (orientations[index] == currentDirection) {
                currentDistance += pathDistances[index]
            } else {
                if (currentDirection != null) {
                    print("Go $currentDistance m $currentDirection, ")
                }
                currentDirection = orientations[index]
                currentDistance = pathDistances[index]
            }
        }
    }

    // Print the last segment
    if (currentDirection != null) {
        print("Go $currentDistance m $currentDirection to Hallway Node ${end.nodeId}")
    }

    println()
}