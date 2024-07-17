package com.example.model.MC

fun buildGraph(): Map<Int, HallwayNode> {
    println("Classrooms created")

    // Create hallway nodes
    val hallways = createHallways()

    println("Hallways created")

    // Connect hallways
    connectHallways(hallways)

    println("Hallways connected")

    // Set distances between hallways in the global distances map
    setDistances(hallways)

    println("Distances set")

    // Connect classrooms to hallways
    connectClassroomsToHallways(hallways)

    println("Classrooms connected")

    return hallways
}

fun createHallways(): Map<Int, HallwayNode> {
    return (601..687).associateWith { HallwayNode(nodeId = it) }
}

fun connectClassroomsToHallways(hallways: Map<Int, HallwayNode>) {
   hallways[601]?.classrooms?.addAll(listOf(6042, 6044, 6046))
   hallways[602]?.classrooms?.addAll(listOf(6038, 6496, 6036))
   hallways[603]?.classrooms?.addAll(listOf(6036, 6034))
   hallways[604]?.classrooms?.addAll(listOf(6033, 6032, 6029))
   hallways[605]?.classrooms?.addAll(listOf(6028))
   hallways[606]?.classrooms?.addAll(listOf(6024, 6026))
   hallways[607]?.classrooms?.addAll(listOf(6018, 60111, 6027, 6022))
   hallways[608]?.classrooms?.addAll(listOf(6012, 6014))
   hallways[609]?.classrooms?.addAll(listOf(6008))
   hallways[610]?.classrooms?.addAll(listOf(6006, 6004, 6002))
   hallways[611]?.classrooms?.addAll(listOf(6086)) //stairs
   hallways[612]?.classrooms?.addAll(listOf(6496))
   hallways[614]?.classrooms?.addAll(listOf(6029))
   hallways[615]?.classrooms?.addAll(listOf(6027, 60111))
   hallways[616]?.classrooms?.addAll(listOf(6011))
   hallways[618]?.classrooms?.addAll(listOf(6808))
   hallways[619]?.classrooms?.addAll(listOf(6912, 6811)) //6811 is an elevator
   hallways[620]?.classrooms?.add(6138)
   hallways[622]?.classrooms?.addAll(listOf(6136, 6137, 6134))
   hallways[623]?.classrooms?.addAll(listOf(6403))
   hallways[624]?.classrooms?.addAll(listOf(6483))
   hallways[625]?.classrooms?.add(listOf(64821, 6302))
   hallways[626]?.classrooms?.addAll(listOf(64824))
   hallways[627]?.classrooms?.addAll(listOf(6133, 6132))
   hallways[628]?.classrooms?.addAll(listOf(6404, 6407, 6408, 6409))
   hallways[629]?.classrooms?.addAll(listOf(6128, 6131))
   hallways[630]?.classrooms?.addAll(listOf(6478))
   hallways[631]?.classrooms?.addAll(listOf(6309, 6303, 6308))
   hallways[632]?.classrooms?.addAll(listOf(6126, 6127, 6124))
   hallways[633]?.classrooms?.addAll(listOf(6412))
   hallways[634]?.classrooms?.addAll(listOf(6501))
   hallways[635]?.classrooms?.addAll(listOf(6503, 6504))
   hallways[636]?.classrooms?.addAll(listOf(6507, 6509, 6508))
   hallways[637]?.classrooms?.addAll(listOf(6511, 6512))
   hallways[638]?.classrooms?.addAll(listOf(6513))
   hallways[639]?.classrooms?.addAll(listOf(6472))
   hallways[640]?.classrooms?.addAll(listOf(6312, 6311, 6313, 6314))
   hallways[641]?.classrooms?.addAll(listOf(6122, 6118, 6123, 6119))
   hallways[642]?.classrooms?.addAll(listOf(6413, 6414))
   hallways[643]?.classrooms?.addAll(listOf(6528, 6526, 6524))
   hallways[644]?.classrooms?.addAll(listOf(6522))
   hallways[645]?.classrooms?.addAll(listOf(6514, 6471, 6516))
   hallways[646]?.classrooms?.addAll(listOf(6473))
   hallways[647]?.classrooms?.addAll(listOf(6316, 6317))
   hallways[648]?.classrooms?.addAll(listOf(6417))
   hallways[649]?.classrooms?.addAll(listOf(6471, 6469, 6460, 6470))
   hallways[650]?.classrooms?.addAll(listOf(6321, 6318, 6322))
   hallways[651]?.classrooms?.addAll(listOf(6116, 6114))
   hallways[652]?.classrooms?.addAll(listOf(6418, 6419, 6421))
   hallways[653]?.classrooms?.addAll(listOf(6467))
   hallways[654]?.classrooms?.addAll(listOf(6323, 6324, 6326))
   hallways[655]?.classrooms?.addAll(listOf(6108, 6112, 6113, 6109))
   hallways[656]?.classrooms?.addAll(listOf(6422, 6423, 6426, 6427, 6428))
   hallways[657]?.classrooms?.addAll(listOf(6461, 6460))
   hallways[658]?.classrooms?.addAll(listOf(6326, 6327))
   hallways[659]?.classrooms?.addAll(listOf(6106, 6104, 6103))
   hallways[660]?.classrooms?.addAll(listOf(6429, 6431, 6432))
   hallways[661]?.classrooms?.addAll(listOf(6459, 6457))
   hallways[662]?.classrooms?.addAll(listOf(6342))
   hallways[663]?.classrooms?.addAll(listOf(6331, 6332, 6328, 6334))
   hallways[664]?.classrooms?.addAll(listOf(6803)) //elevator
   hallways[665]?.classrooms?.addAll(listOf(6101))
   hallways[666]?.classrooms?.addAll(listOf(6434))
   hallways[667]?.classrooms?.addAll(listOf(6436, 6437, 6439))
   hallways[668]?.classrooms?.addAll(listOf(6441, 6443))
   hallways[669]?.classrooms?.addAll(listOf(6447, 6449))
   hallways[670]?.classrooms?.addAll(listOf(6451, 6452))
   hallways[671]?.classrooms?.addAll(listOf(6453, 6454))
   hallways[672]?.classrooms?.addAll(listOf(6917))
   hallways[673]?.classrooms?.addAll(listOf(6902, 6201))
   hallways[674]?.classrooms?.addAll(listOf(64441, 64446))
   hallways[675]?.classrooms?.addAll(listOf(6813)) //stairs
   hallways[676]?.classrooms?.addAll(listOf(6202, 6203))
   hallways[677]?.classrooms?.addAll(listOf(64442, 64443, 64444, 64445))
   hallways[678]?.classrooms?.addAll(listOf(6452, 6254))
   hallways[679]?.classrooms?.addAll(listOf(6204, 6206))
   hallways[680]?.classrooms?.addAll(listOf(6252, 6248))
   hallways[681]?.classrooms?.addAll(listOf(6208, 6212))
   hallways[682]?.classrooms?.addAll(listOf(6217, 6216, 6219, 6218))
   hallways[683]?.classrooms?.addAll(listOf(6221, 6222, 6223, 6224))
   hallways[684]?.classrooms?.addAll(listOf(6227, 6226, 6228, 6229))
   hallways[685]?.classrooms?.addAll(listOf(6231, 6232, 6233, 6234))
   hallways[686]?.classrooms?.addAll(listOf(6237, 6236, 6239, 6238))
   hallways[687]?.classrooms?.addAll(listOf(6244, 6246))

}




fun createClassroomToHallwayMap(hallways: Map<Int, HallwayNode>): Map<Int, List<Int>> {
    val map = mutableMapOf<Int, MutableList<Int>>()
    hallways.forEach { (hallwayId, hallway) ->
        hallway.classrooms.forEach { classroomId ->
            if (map.containsKey(classroomId)) {
                map[classroomId]?.add(hallwayId)
            } else {
                map[classroomId] = mutableListOf(hallwayId)
            }
        }
    }
    return map
}