package com.example.graph

import com.example.graph.*
import java.util.PriorityQueue
import NodeStructure.*

fun dijkstra(start: HallwayNode): Map<HallwayNode, Int> {
    val distances = mutableMapOf<HallwayNode, Int>().withDefault { Int.MAX_VALUE }
    val priorityQueue = PriorityQueue<Pair<HallwayNode, Int>>(compareBy { it.second })

    priorityQueue.add(start to 0)
    distances[start] = 0

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
