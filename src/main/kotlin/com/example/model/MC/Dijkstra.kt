package com.example.model.MC

import java.util.PriorityQueue

fun dijkstra(start: HallwayNode): Map<HallwayNode, Double> {
    val distances = mutableMapOf<HallwayNode, Double>().withDefault { Double.MAX_VALUE }
    val priorityQueue = PriorityQueue<Pair<HallwayNode, Double>>(compareBy { it.second })

    priorityQueue.add(start to 0.0)
    distances[start] = 0.0

    while (priorityQueue.isNotEmpty()) {
        val (currentNode, currentDistance) = priorityQueue.poll()

        if (currentDistance > distances.getValue(currentNode)) continue

        val neighbors = listOfNotNull(currentNode.north, currentNode.south, currentNode.east, currentNode.west)
        for (neighbor in neighbors) {
            val distance = globalDistances[currentNode to neighbor] ?: continue
            val newDistance = currentDistance + distance
            if (newDistance < distances.getValue(neighbor)) {
                distances[neighbor] = newDistance
                priorityQueue.add(neighbor to newDistance)
            }
        }
    }

    return distances
}
