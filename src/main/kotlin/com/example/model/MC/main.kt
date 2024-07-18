package com.example.model.MC

import com.example.model.MC.buildGraphFloor1
import com.example.model.MC.buildGraphFloor2
import com.example.model.MC.buildGraphFloor3
import com.example.model.MC.buildGraphFloor4
import com.example.model.MC.buildGraphFloor5
import com.example.model.MC.buildGraphFloor6

fun main() {
    val hallwaysFloor1 = buildGraphFloor1()
    val hallwaysFloor2 = buildGraphFloor2()
    val hallwaysFloor3 = buildGraphFloor3()
    val hallwaysFloor4 = buildGraphFloor4()
    val hallwaysFloor5 = buildGraphFloor5()
    val hallwaysFloor6 = buildGraphFloor6()

    // Combine all hallways into a single map
    val hallways: MutableMap<Int, HallwayNode> = mutableMapOf()
    hallways.putAll(hallwaysFloor1)
    hallways.putAll(hallwaysFloor2)
    hallways.putAll(hallwaysFloor3)
    hallways.putAll(hallwaysFloor4)
    hallways.putAll(hallwaysFloor5)
    hallways.putAll(hallwaysFloor6)

    // Create the classroom to hallway map
    val classroomToHallwayMap = createClassroomToHallwayMap(hallways)

    // Change these to the desired starting and ending classroom IDs
    val startClassroomId = 1081
    val endClassroomId = 10602
    val useElevator = False

    dijkstra(startClassroomId, endClassroomId, hallways, classroomToHallwayMap, useElevator)
}
