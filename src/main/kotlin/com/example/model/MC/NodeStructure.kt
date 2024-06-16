package com.example.model.MC

data class HallwayNode(
    val nodeId: Int,                        // Identifier for the hallway node
    var north: HallwayNode? = null,         // North neighboring hallway node
    var south: HallwayNode? = null,         // South neighboring hallway node
    var east: HallwayNode? = null,          // East neighboring hallway node
    var west: HallwayNode? = null,          // West neighboring hallway node
    val classrooms: MutableList<ClassroomNode> = mutableListOf()  // Connected classroom nodes
) {
    override fun hashCode(): Int {
        return nodeId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HallwayNode) return false
        return nodeId == other.nodeId
    }
}

data class ClassroomNode(
    val roomId: Int,       // Identifier for the classroom
    var connectedHallway: HallwayNode? = null // Connected hallway node
)

val globalDistances = mutableMapOf<Pair<HallwayNode, HallwayNode>, Int>()

fun printMap(hallways: List<HallwayNode>) {
    hallways.forEach { hallway ->
        println("Hallway ${hallway.nodeId}:")
        hallway.north?.let { println("  North: Hallway ${it.nodeId}") }
        hallway.south?.let { println("  South: Hallway ${it.nodeId}") }
        hallway.east?.let { println("  East: Hallway ${it.nodeId}") }
        hallway.west?.let { println("  West: Hallway ${it.nodeId}") }
        if (hallway.classrooms.isNotEmpty()) {
            println("  Classrooms: ${hallway.classrooms.joinToString { it.roomId.toString() }}")
        }
        println()
    }

    println("Global Distances:")
    globalDistances.forEach { (nodes, distance) ->
        println("  Distance from Hallway ${nodes.first.nodeId} to Hallway ${nodes.second.nodeId}: $distance")
    }
}

fun main() {
    // Create classroom nodes
    val classroom1 = ClassroomNode(roomId = 101)
    val classroom2 = ClassroomNode(roomId = 102)
    val classroom3 = ClassroomNode(roomId = 103)

    println("Classrooms created")

    // Create hallway nodes
    val hallway1 = HallwayNode(nodeId = 1)
    val hallway2 = HallwayNode(nodeId = 2)
    val hallway3 = HallwayNode(nodeId = 3)

    println("Hallways created")

    // Connect hallways
    hallway1.east = hallway2
    hallway2.west = hallway1
    hallway2.south = hallway3
    hallway3.north = hallway2

    println("Hallways connected")

    // Set distances between hallways in the global distances map
    globalDistances[hallway1 to hallway2] = 10
    globalDistances[hallway2 to hallway1] = 10
    globalDistances[hallway2 to hallway3] = 15
    globalDistances[hallway3 to hallway2] = 15

    println("Distances set")

    // Connect classrooms to hallways
    hallway1.classrooms.add(classroom1)
    hallway2.classrooms.add(classroom2)
    hallway3.classrooms.add(classroom3)

    println("Classrooms connected")

    // Connect classrooms to hallways
    classroom1.connectedHallway = hallway1
    classroom2.connectedHallway = hallway2
    classroom3.connectedHallway = hallway3

    // Print the map structure
    printMap(listOf(hallway1, hallway2, hallway3))
}
