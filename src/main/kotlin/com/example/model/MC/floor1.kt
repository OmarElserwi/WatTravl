package com.example.graph
import com.example.graph.*
import NodeStructure.*

fun buildGraph(): List<HallwayNode> {
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

    return listOf(hallway1, hallway2, hallway3)
}
