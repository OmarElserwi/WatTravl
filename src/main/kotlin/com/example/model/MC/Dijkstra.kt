package com.example.model.MC

import java.util.PriorityQueue

val staircases = mapOf(
    1 to listOf(1099, 1106, 1096, 1091),
    2 to listOf(2077, 2099, 2074, 2068),
    3 to listOf(3091, 3095, 3089, 3080),
    4 to listOf(4111, 4122, 4108, 4101),
    5 to listOf(5808, 5813, 5806, 5801),
    6 to listOf(6808, 6813, 6806, 6801)
)

val elevators = mapOf(
    1 to listOf(1100, 1092),
    2 to listOf(2078, 2070),
    3 to listOf(3093, 3082),
    4 to listOf(4115, 4104),
    5 to listOf(5811, 5803),
    6 to listOf(6811, 6803)
)

fun dijkstra(startClassroomId: Int, endClassroomId: Int, hallways: Map<Int, HallwayNode>, classroomToHallwayMap: Map<Int, List<Int>>) {
    val startNodeIds = classroomToHallwayMap[startClassroomId] ?: throw IllegalArgumentException("Classroom $startClassroomId does not exist in the map")
    val endNodeIds = classroomToHallwayMap[endClassroomId] ?: throw IllegalArgumentException("Classroom $endClassroomId does not exist in the map")
    
    if (false) {//endClassroomId - startClassroomId > 1000) {
        if (endClassroomId - startClassroomId >= 2000) {

        }
        else { //use stairs instead

        }
        

    }
    else { // both nodes are on the same floor
        val targetEndHallwayNodes = classroomToHallwayMap[endClassroomId] ?: throw IllegalArgumentException("End classroom ID $endClassroomId does not exist in the map")
        var minDistance = Double.MAX_VALUE
        var closestEndNode: HallwayNode? = null
        var closestStartNode: HallwayNode? = null
        var bestDistances: Map<HallwayNode, Pair<Double, HallwayNode?>>? = null

        for (startNodeId in startNodeIds) {
            val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
            val distances = dijkstraInternal(startNode, hallways)
            distances.forEach { (hallway, distance) ->
                println("Distance from ${startNode.nodeId} to ${hallway.nodeId}: ${distance.first}")
            }
            for (targetEndHallwayNode in targetEndHallwayNodes) {
                val targetNode = hallways[targetEndHallwayNode]
                val distanceToTarget = distances[targetNode]?.first ?: throw IllegalArgumentException("Distance to hallway node $targetNode not found")
                if (distanceToTarget < minDistance) {
                    minDistance = distanceToTarget
                    closestEndNode = targetNode
                    closestStartNode = startNode
                    bestDistances = distances
                }
            }
        }

        if (closestStartNode != null && closestEndNode != null && bestDistances != null) {
            println("Distance from start node to hallway node ${closestEndNode.nodeId}: $minDistance")
            printPath(bestDistances, closestStartNode, closestEndNode)
        } else {
            println("No path found from start nodes to end nodes.")
        }
    }
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


fun dijkstraInternal(start: HallwayNode, hallways: Map<Int, HallwayNode>): Map<HallwayNode, Pair<Double, HallwayNode?>> {
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
