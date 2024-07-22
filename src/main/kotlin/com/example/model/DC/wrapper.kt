package com.example.model.DC

import com.example.model.DC.buildGraphFloor1
import com.example.model.DC.buildGraphFloor2
import com.example.model.DC.buildGraphFloor3

fun dc_pathing():List<List<HallwayNode>> {
    val hallwaysFloor1 = buildGraphFloor1()
    val hallwaysFloor2 = buildGraphFloor2()
    val hallwaysFloor3 = buildGraphFloor3()

    // Combine all hallways into a single map
    val hallways: MutableMap<Int, HallwayNode> = mutableMapOf()
    hallways.putAll(hallwaysFloor1)
    hallways.putAll(hallwaysFloor2)
    hallways.putAll(hallwaysFloor3)

    // Create the classroom to hallway map
    val classroomToHallwayMap = createClassroomToHallwayMap(hallways)

    // Change these to the desired starting and ending classroom IDs
    val startClassroomId = 2643
    val enDClassroomId = 3853
    val useElevator = false

    return dijkstra(startClassroomId, enDClassroomId, hallways, classroomToHallwayMap, useElevator)

}
