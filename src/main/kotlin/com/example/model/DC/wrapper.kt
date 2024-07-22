package com.example.model.DC

import com.example.model.DC.buildGraphFloor1
import com.example.model.DC.buildGraphFloor2
import com.example.model.DC.buildGraphFloor3

fun dc_pathing(start: Int, end: Int, useElevator: Boolean):List<List<HallwayNode>> {
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

    return dijkstra(start, end, hallways, classroomToHallwayMap, useElevator)

}
