package com.example.model.DC

import java.util.PriorityQueue

val staircases = mapOf(
    1 to listOf(18411, 18412, 18521, 18421, 18422),
    2 to listOf(2809, 2807, 2829, 2805, 2827, 2810, 2828, 2808, 2806, 28411, 28412, 2804, 2803, 2802, 2801, 2811, 28363, 2812),
    3 to listOf(3809, 3807, 3805, 3806, 3808, 3810, 3803, 3804, 3802, 3801, 38364, 3811, 3812, 37017, 38363),
)

val elevators = mapOf(
    1 to listOf(1818),
    2 to listOf(2817, 2818),
    3 to listOf(3817, 3818),
)

fun findFloor(number: Int): Int {
    val numberStr = number.toString()
    return numberStr[0].toString().toInt()
}

fun finDCorrespondingTransition(startingTransition: Int, endingFloor: Int, useElevator: Boolean): Int? {
    val transitions = if (useElevator) elevators else staircases

    // Find the index of the startingTransition in its corresponding floor list
    val startFloorIndex = transitions.values.indexOfFirst { it.contains(startingTransition) }
    
    // If the startingTransition is found in one of the floor lists
    if (startFloorIndex != -1) {
        // Get the list of transitions for the starting floor
        val startTransitions = transitions[startFloorIndex + 1]
        
        // Find the index of the startingTransition in its list
        val transitionIndex = startTransitions?.indexOf(startingTransition)
        
        // If the index is found
        if (transitionIndex != -1) {
            // Get the list of transitions for the ending floor
            val endTransitions = transitions[endingFloor]
            
            // Return the transition at the same index for the ending floor
            return endTransitions?.get(transitionIndex!!)
        }
    }
    
    // If the startingTransition is not found or index is invalid, return null
    return null
}

fun dijkstra(startClassroomId: Int, enDClassroomId: Int, hallways: Map<Int, HallwayNode>, classroomToHallwayMap: Map<Int, List<Int>>, useElevator: Boolean): List<List<HallwayNode>>{
    var hallwayNodesList = mutableListOf<List<HallwayNode>>()
    val startNodeIds = classroomToHallwayMap[startClassroomId] ?: throw IllegalArgumentException("Classroom $startClassroomId does not exist in the map")
    val endNodeIds = classroomToHallwayMap[enDClassroomId] ?: throw IllegalArgumentException("Classroom $enDClassroomId does not exist in the map")
    val startingFloor = findFloor(startClassroomId)
    val endingFloor = findFloor(enDClassroomId)
    val targetEndHallwayNodes = classroomToHallwayMap[enDClassroomId] ?: throw IllegalArgumentException("End classroom ID $enDClassroomId does not exist in the map")
    if (endingFloor != startingFloor) { //use stairs or elevator
        val floor = findFloor(startNodeIds[0])
        val transitions = if (useElevator || endingFloor - startingFloor > 1) {
            elevators[floor] ?: throw IllegalArgumentException("Elevators not found")
        } else {
            staircases[floor] ?: throw IllegalArgumentException("Staircases not found")
        }
        var minDistance = Double.MAX_VALUE
        var closestTransition: HallwayNode? = null
        var closestTransitionNum: Int? = null
        var closestStartNode: HallwayNode? = null
        var bestDistances: Map<HallwayNode, Pair<Double, HallwayNode?>>? = null
        
        for (startNodeId in startNodeIds) {
            val startNode = hallways[startNodeId] ?: throw IllegalArgumentException("Hallway node $startNodeId does not exist")
            val distances = dijkstraInternal(startNode, hallways)
            for (t in transitions) {
                println("${t}")
                val targetNode = classroomToHallwayMap[t]?.getOrNull(0)?.let { hallways[it] } //staircases only have one entrance
                val distanceToTarget = distances[targetNode]?.first ?: throw IllegalArgumentException("Distance to hallway node $targetNode not found${targetNode}")
                if (distanceToTarget < minDistance) {
                    minDistance = distanceToTarget
                    closestTransition = targetNode
                    closestStartNode = startNode
                    bestDistances = distances
                    closestTransitionNum = t
                }
            }
        }
        if (closestStartNode != null && closestTransition != null && closestTransitionNum != null && bestDistances != null) {
            var minDistance2 = Double.MAX_VALUE
            var closestEndNode2: HallwayNode? = null
            var closestStartNode2: HallwayNode? = null
            var bestDistances2: Map<HallwayNode, Pair<Double, HallwayNode?>>? = null
            hallwayNodesList.add(printPath(bestDistances, closestStartNode, closestTransition))
            val newStart: Int? = finDCorrespondingTransition(closestTransitionNum, findFloor(enDClassroomId), useElevator || endingFloor - startingFloor > 1)
            val newStartNodeNum: Int = classroomToHallwayMap[newStart]?.firstOrNull() ?: throw IllegalArgumentException("Hallway node $newStart does not exist")
            val newStartNode: HallwayNode = hallways[newStartNodeNum] ?: throw IllegalArgumentException("Hallway node $newStartNodeNum does not exist")
            val distances2: Map<HallwayNode, Pair<Double, HallwayNode?>> = dijkstraInternal(newStartNode, hallways)
            if (useElevator || endingFloor - startingFloor > 1) {
                println("take elevator ${closestTransitionNum} ...")
                println("get off elevator at ${newStart}...")
            }
            else {
                println("take staircase ${closestTransitionNum} ...")
                println("get off stairs at ${newStart}...")
            }
            for (targetEndHallwayNode in targetEndHallwayNodes) {
                val targetNode = hallways[targetEndHallwayNode]
                val distanceToTarget = distances2[targetNode]?.first ?: throw IllegalArgumentException("Distance to hallway node $targetNode not found")
                if (distanceToTarget < minDistance2) {
                    minDistance2 = distanceToTarget
                    closestEndNode2 = targetNode
                    closestStartNode2 = newStartNode
                    bestDistances2 = distances2
                }
            }
            if (closestStartNode2 != null && closestEndNode2 != null && bestDistances2 != null) {
                hallwayNodesList.add(printPath(bestDistances2, closestStartNode2, closestEndNode2))
            } else {
                println("No path found from start nodes to end nodes.")
            }
        } else {
            println("No path found from start nodes to end nodes.")
        }
    }
    else { // both nodes are on the same floor
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
            hallwayNodesList.add(printPath(bestDistances, closestStartNode, closestEndNode))
        } else {
            println("No path found from start nodes to end nodes.")
        }
    }
    return hallwayNodesList
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
