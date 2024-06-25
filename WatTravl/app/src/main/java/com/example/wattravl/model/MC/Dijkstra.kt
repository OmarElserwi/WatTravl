package com.example.wattravl.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.PriorityQueue

@RequiresApi(Build.VERSION_CODES.N)
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

fun printPath(distances: Map<HallwayNode, Pair<Double, HallwayNode?>>, start: HallwayNode, end: HallwayNode): MutableList<HallwayNode> {
    val path = mutableListOf<HallwayNode>()
    var currentNode: HallwayNode? = end

    while (currentNode != null) {
        path.add(currentNode)
        currentNode = distances[currentNode]?.second
    }

    path.reverse()

    println("Path from ${start.nodeId} to ${end.nodeId}:")
    println(path.joinToString(" -> ") { it.nodeId.toString() })
    return path
}

fun getPath(distances: Map<HallwayNode, Pair<Double, HallwayNode?>>, start: HallwayNode, end: HallwayNode): MutableList<HallwayNode> {
    val path = mutableListOf<HallwayNode>()
    var currentNode: HallwayNode? = end


    while (currentNode != null) {
        path.add(currentNode)
        currentNode = distances[currentNode]?.second
    }

    path.reverse()
    return path
}
