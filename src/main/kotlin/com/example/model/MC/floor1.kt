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
    return (101..155).associateWith { HallwayNode(nodeId = it) }
}

fun connectHallways(hallways: Map<Int, HallwayNode>) {
    hallways[101]?.apply {
        east = hallways[102]
        south = hallways[109]
    }
    
    hallways[102]?.apply {
        west = hallways[101]
        east = hallways[103]
    }
    hallways[103]?.apply {
        west = hallways[102]
        east = hallways[104]
    }
    hallways[104]?.apply {
        west = hallways[103]
        east = hallways[105]
    }
    hallways[105]?.apply {
        west = hallways[104]
        east = hallways[106]
    }
    hallways[106]?.apply {
        west = hallways[105]
        east = hallways[107]
    }
    hallways[107]?.apply {
        west = hallways[106]
        east = hallways[108]
    }
    hallways[108]?.apply {
        west = hallways[107]
        south = hallways[114]
    }
    hallways[109]?.apply {
        north = hallways[101]
        east = hallways[110]
        south = hallways[116]
    }
    hallways[110]?.apply {
        west = hallways[109]
        east = hallways[111]
    }
    hallways[111]?.apply {
        west = hallways[110]
        east = hallways[112]
    }
    hallways[112]?.apply {
        west = hallways[111]
        east = hallways[113]
    }
    hallways[113]?.apply {
        west = hallways[112]
        east = hallways[114]
    }
    hallways[114]?.apply {
        west = hallways[113]
        east = hallways[115]
        north = hallways[108]
        south = hallways[117]
    }
    hallways[115]?.apply {
        west = hallways[114]
    }
    hallways[116]?.apply {
        north = hallways[109]
        south = hallways[118]
    }
    hallways[117]?.apply {
        north = hallways[114]
        east = hallways[119]
    }
    hallways[118]?.apply {
        north = hallways[116]
        south = hallways[120]
    }
    hallways[119]?.apply {
        north = hallways[117]
        south = hallways[121]
    }
    hallways[120]?.apply {
        north = hallways[118]
        south = hallways[122]
    }
    hallways[121]?.apply {
        north = hallways[119]
        south = hallways[126]
    }
    hallways[122]?.apply {
        north = hallways[120]
        south = hallways[127]
        east = hallways[123]
    }
    hallways[123]?.apply {
        west = hallways[122]
        east = hallways[124]
    }
    hallways[124]?.apply {
        west = hallways[123]
        east = hallways[125]
    }
    hallways[125]?.apply {
        west = hallways[124]
        east = hallways[126]
    }
    hallways[126]?.apply {
        west = hallways[125]
        north = hallways[121]
        south = hallways[129]
    }
    hallways[127]?.apply {
        north = hallways[122]
        south = hallways[130]
    }
    hallways[128]?.apply {
        north = hallways[123]
        south = hallways[131]
    }
    hallways[129]?.apply {
        north = hallways[126]
        south = hallways[133]
    }
    hallways[130]?.apply {
        north = hallways[127]
        south = hallways[134]
    }
    hallways[131]?.apply {
        north = hallways[128]
        south = hallways[135]
        east = hallways[132]
    }
    hallways[132]?.apply {
        west = hallways[131]
    }
    hallways[133]?.apply {
        north = hallways[129]
        south = hallways[136]
    }
    hallways[134]?.apply {
        north = hallways[130]
        south = hallways[137]
    }
    hallways[135]?.apply {
        north = hallways[131]
        south = hallways[138]
    }
    hallways[136]?.apply {
        north = hallways[133]
        south = hallways[141]
    }
    hallways[137]?.apply {
        north = hallways[134]
        south = hallways[139]
    }
    hallways[138]?.apply {
        north = hallways[135]
        south = hallways[140]
    }
    hallways[139]?.apply {
        north = hallways[137]
        south = hallways[143]
    }
    hallways[140]?.apply {
        north = hallways[138]
        south = hallways[144]
    }
    hallways[141]?.apply {
        north = hallways[136]
        south = hallways[151]
    }
    hallways[142]?.apply {
        east = hallways[143]
    }
    hallways[143]?.apply {
        north = hallways[139]
        south = hallways[145]
        east = hallways[142]
    }
    hallways[144]?.apply {
        north = hallways[140]
        south = hallways[147]
    }
    hallways[145]?.apply {
        north = hallways[143]
        south = hallways[152]
    }

    hallways[146]?.apply {
        west = hallways[145]
        east = hallways[147]
    }
    hallways[147]?.apply {
        west = hallways[146]
        east = hallways[148]
        north = hallways[144]
    }
    hallways[148]?.apply {
        west = hallways[147]
        east = hallways[149]
    }
    hallways[149]?.apply {
        west = hallways[148]
        east = hallways[150]
    }
    hallways[150]?.apply {
        west = hallways[149]
        east = hallways[151]
    }
    hallways[151]?.apply {
        west = hallways[150]
        north = hallways[141]
    }
    hallways[152]?.apply {
        north = hallways[145]
        south = hallways[153]
    }
    hallways[153]?.apply {
        north = hallways[152]
    }
}

val unit = 1.0  // Example value for unit

fun setDistances(hallways: Map<Int, HallwayNode>) {
    globalDistances[hallways[101]!! to hallways[102]!!] = 1 * unit
    globalDistances[hallways[102]!! to hallways[101]!!] = 1 * unit
    
    globalDistances[hallways[102]!! to hallways[103]!!] = 1.5 * unit
    globalDistances[hallways[103]!! to hallways[102]!!] = 1.5 * unit
    
    globalDistances[hallways[103]!! to hallways[104]!!] = 1.5 * unit
    globalDistances[hallways[104]!! to hallways[103]!!] = 1.5 * unit
    
    globalDistances[hallways[104]!! to hallways[105]!!] = 1.5 * unit
    globalDistances[hallways[105]!! to hallways[104]!!] = 1.5 * unit
    
    globalDistances[hallways[105]!! to hallways[106]!!] = 1 * unit
    globalDistances[hallways[106]!! to hallways[105]!!] = 1 * unit
    
    globalDistances[hallways[106]!! to hallways[107]!!] = 1.5 * unit
    globalDistances[hallways[107]!! to hallways[106]!!] = 1.5 * unit
    
    globalDistances[hallways[107]!! to hallways[108]!!] = 1 * unit
    globalDistances[hallways[108]!! to hallways[107]!!] = 1 * unit

    globalDistances[hallways[101]!! to hallways[109]!!] = 1.5 * unit
    globalDistances[hallways[109]!! to hallways[101]!!] = 1.5 * unit
    
    globalDistances[hallways[108]!! to hallways[114]!!] = 1.5 * unit
    globalDistances[hallways[114]!! to hallways[108]!!] = 1.5 * unit
    
    globalDistances[hallways[109]!! to hallways[110]!!] = 0.5 * unit
    globalDistances[hallways[110]!! to hallways[109]!!] = 0.5 * unit
    
    globalDistances[hallways[110]!! to hallways[111]!!] = 0.8 * unit
    globalDistances[hallways[111]!! to hallways[110]!!] = 0.8 * unit
    
    globalDistances[hallways[111]!! to hallways[112]!!] = 1 * unit
    globalDistances[hallways[112]!! to hallways[111]!!] = 1 * unit
    
    globalDistances[hallways[112]!! to hallways[113]!!] = 2 * unit
    globalDistances[hallways[113]!! to hallways[112]!!] = 2 * unit
    
    globalDistances[hallways[113]!! to hallways[114]!!] = 2.5 * unit
    globalDistances[hallways[114]!! to hallways[113]!!] = 2.5 * unit
    
    globalDistances[hallways[114]!! to hallways[115]!!] = 2 * unit
    globalDistances[hallways[115]!! to hallways[114]!!] = 2 * unit
    
    globalDistances[hallways[109]!! to hallways[116]!!] = 1.5 * unit
    globalDistances[hallways[116]!! to hallways[109]!!] = 1.5 * unit
    
    globalDistances[hallways[114]!! to hallways[117]!!] = 1.2 * unit
    globalDistances[hallways[117]!! to hallways[114]!!] = 1.2 * unit
    
    globalDistances[hallways[116]!! to hallways[118]!!] = 1.5 * unit
    globalDistances[hallways[118]!! to hallways[116]!!] = 1.5 * unit
    
    globalDistances[hallways[117]!! to hallways[119]!!] = 1.5 * unit
    globalDistances[hallways[119]!! to hallways[117]!!] = 1.5 * unit
    
    globalDistances[hallways[118]!! to hallways[120]!!] = 1.5 * unit
    globalDistances[hallways[120]!! to hallways[118]!!] = 1.5 * unit
    
    globalDistances[hallways[119]!! to hallways[121]!!] = 1.5 * unit
    globalDistances[hallways[121]!! to hallways[119]!!] = 1.5 * unit
    
    globalDistances[hallways[120]!! to hallways[122]!!] = 2 * unit
    globalDistances[hallways[122]!! to hallways[120]!!] = 2 * unit
    
    globalDistances[hallways[121]!! to hallways[126]!!] = 1.5 * unit
    globalDistances[hallways[126]!! to hallways[121]!!] = 1.5 * unit
    
    globalDistances[hallways[122]!! to hallways[123]!!] = 2 * unit
    globalDistances[hallways[123]!! to hallways[122]!!] = 2 * unit
    
    globalDistances[hallways[123]!! to hallways[124]!!] = 2.5 * unit
    globalDistances[hallways[124]!! to hallways[123]!!] = 2.5 * unit
    
    globalDistances[hallways[124]!! to hallways[125]!!] = 1.7 * unit
    globalDistances[hallways[125]!! to hallways[124]!!] = 1.7 * unit
    
    globalDistances[hallways[125]!! to hallways[126]!!] = 2 * unit
    globalDistances[hallways[126]!! to hallways[125]!!] = 2 * unit
    
    globalDistances[hallways[122]!! to hallways[127]!!] = 1 * unit
    globalDistances[hallways[127]!! to hallways[122]!!] = 1 * unit
    
    globalDistances[hallways[123]!! to hallways[128]!!] = 1 * unit
    globalDistances[hallways[128]!! to hallways[123]!!] = 1 * unit
    
    globalDistances[hallways[126]!! to hallways[129]!!] = 1 * unit
    globalDistances[hallways[129]!! to hallways[126]!!] = 1 * unit
    
    globalDistances[hallways[127]!! to hallways[130]!!] = 1.2 * unit
    globalDistances[hallways[130]!! to hallways[127]!!] = 1.2 * unit
    
    globalDistances[hallways[128]!! to hallways[131]!!] = 1.2 * unit
    globalDistances[hallways[131]!! to hallways[128]!!] = 1.2 * unit
    
    globalDistances[hallways[129]!! to hallways[133]!!] = 1.3 * unit
    globalDistances[hallways[133]!! to hallways[129]!!] = 1.3 * unit
    
    globalDistances[hallways[130]!! to hallways[134]!!] = 1 * unit
    globalDistances[hallways[134]!! to hallways[130]!!] = 1 * unit
    
    globalDistances[hallways[131]!! to hallways[132]!!] = 0.8 * unit
    globalDistances[hallways[132]!! to hallways[131]!!] = 0.8 * unit
    
    globalDistances[hallways[133]!! to hallways[136]!!] = 1.2 * unit
    globalDistances[hallways[136]!! to hallways[133]!!] = 1.2 * unit
    
    globalDistances[hallways[134]!! to hallways[137]!!] = 1 * unit
    globalDistances[hallways[137]!! to hallways[134]!!] = 1 * unit
    
    globalDistances[hallways[135]!! to hallways[138]!!] = 1 * unit
    globalDistances[hallways[138]!! to hallways[135]!!] = 1 * unit
    
    globalDistances[hallways[136]!! to hallways[141]!!] = 1.5 * unit
    globalDistances[hallways[141]!! to hallways[136]!!] = 1.5 * unit
    
    globalDistances[hallways[137]!! to hallways[139]!!] = 0.2 * unit
    globalDistances[hallways[139]!! to hallways[137]!!] = 0.2 * unit
    
    globalDistances[hallways[138]!! to hallways[140]!!] = 0.2 * unit
    globalDistances[hallways[140]!! to hallways[138]!!] = 0.2 * unit
    
    globalDistances[hallways[141]!! to hallways[151]!!] = 1.3 * unit
    globalDistances[hallways[151]!! to hallways[141]!!] = 1.3 * unit
    
    globalDistances[hallways[139]!! to hallways[143]!!] = 0.4 * unit
    globalDistances[hallways[143]!! to hallways[139]!!] = 0.4 * unit
    
    globalDistances[hallways[140]!! to hallways[144]!!] = 1 * unit
    globalDistances[hallways[144]!! to hallways[140]!!] = 1 * unit
    
    globalDistances[hallways[142]!! to hallways[143]!!] = 2 * unit
    globalDistances[hallways[143]!! to hallways[142]!!] = 2 * unit
    
    globalDistances[hallways[143]!! to hallways[145]!!] = 0.3 * unit
    globalDistances[hallways[145]!! to hallways[143]!!] = 0.3 * unit
    
    globalDistances[hallways[144]!! to hallways[147]!!] = 1 * unit
    globalDistances[hallways[147]!! to hallways[144]!!] = 1 * unit
    
    globalDistances[hallways[145]!! to hallways[146]!!] = 1 * unit
    globalDistances[hallways[146]!! to hallways[145]!!] = 1 * unit
    
    globalDistances[hallways[146]!! to hallways[147]!!] = 0.3 * unit
    globalDistances[hallways[147]!! to hallways[146]!!] = 0.3 * unit
    
    globalDistances[hallways[147]!! to hallways[148]!!] = 1.3 * unit
    globalDistances[hallways[148]!! to hallways[147]!!] = 1.3 * unit
    
    globalDistances[hallways[148]!! to hallways[149]!!] = 0.3 * unit
    globalDistances[hallways[149]!! to hallways[148]!!] = 0.3 * unit
    
    globalDistances[hallways[149]!! to hallways[150]!!] = 1 * unit
    globalDistances[hallways[150]!! to hallways[149]!!] = 1 * unit
    
    globalDistances[hallways[150]!! to hallways[151]!!] = 1.5 * unit
    globalDistances[hallways[151]!! to hallways[150]!!] = 1.5 * unit
    
    globalDistances[hallways[145]!! to hallways[152]!!] = 1 * unit
    globalDistances[hallways[152]!! to hallways[145]!!] = 1 * unit
    
    globalDistances[hallways[152]!! to hallways[153]!!] = 0.5 * unit
    globalDistances[hallways[153]!! to hallways[152]!!] = 0.5 * unit
}

fun connectClassroomsToHallways(hallways: Map<Int, HallwayNode>) {
    // Example connections, adjust based on actual requirements
    hallways[101]?.classrooms?.add(1027)
    hallways[102]?.classrooms?.add(1028)
    hallways[102]?.classrooms?.add(1029)
    hallways[103]?.classrooms?.add(1030)
    hallways[103]?.classrooms?.add(1031)
    hallways[103]?.classrooms?.add(1032)
    hallways[104]?.classrooms?.add(1033)
    hallways[104]?.classrooms?.add(1034)
    hallways[104]?.classrooms?.add(1035)
    hallways[104]?.classrooms?.add(1036)
    hallways[105]?.classrooms?.add(1037)
    hallways[105]?.classrooms?.add(1038)
    hallways[105]?.classrooms?.add(1039)
    hallways[105]?.classrooms?.add(1040)
    hallways[106]?.classrooms?.add(1041)
    hallways[106]?.classrooms?.add(1042)
    hallways[107]?.classrooms?.add(1043)
    hallways[107]?.classrooms?.add(1044)
    hallways[107]?.classrooms?.add(1049)
    hallways[107]?.classrooms?.add(1049) //1049A
    hallways[108]?.classrooms?.add(1045)
    hallways[108]?.classrooms?.add(1046)
    hallways[109]?.classrooms?.add(1096)
    hallways[109]?.classrooms?.add(1026) //bathroom
    hallways[110]?.classrooms?.add(1058)
    hallways[111]?.classrooms?.add(1056)
    hallways[113]?.classrooms?.add(1052)
    hallways[114]?.classrooms?.add(1099)
    hallways[115]?.classrooms?.add(1047) //bathroom
    hallways[115]?.classrooms?.add(1048)
    hallways[115]?.classrooms?.add(1100) //elevators
    hallways[116]?.classrooms?.add(1025)
    hallways[116]?.classrooms?.add(1024)
    hallways[118]?.classrooms?.add(1023)
    hallways[118]?.classrooms?.add(1022)
    hallways[120]?.classrooms?.add(1021)
    hallways[120]?.classrooms?.add(1020)
    hallways[121]?.classrooms?.add(1018)
    hallways[124]?.classrooms?.add(1061)
    hallways[125]?.classrooms?.add(1063)
    hallways[126]?.classrooms?.add(1070)
    hallways[126]?.classrooms?.add(1071)
    hallways[127]?.classrooms?.add(1016)
    hallways[127]?.classrooms?.add(1014)
    hallways[127]?.classrooms?.add(1017)
    hallways[127]?.classrooms?.add(1015)
    hallways[128]?.classrooms?.add(10601) //1060A
    hallways[129]?.classrooms?.add(1073)
    hallways[129]?.classrooms?.add(1072)
    hallways[130]?.classrooms?.add(1012)
    hallways[130]?.classrooms?.add(1013)
    hallways[130]?.classrooms?.add(1010)
    hallways[130]?.classrooms?.add(1011)
    hallways[131]?.classrooms?.add(10602) //1060B
    //hallways[132]?.classrooms?.add(1012) double entrance case
    hallways[131]?.classrooms?.add(10603) //1060C
    hallways[133]?.classrooms?.add(1074)
    hallways[133]?.classrooms?.add(1076)
    hallways[133]?.classrooms?.add(1077)
    hallways[134]?.classrooms?.add(1006)
    hallways[134]?.classrooms?.add(1007)
    hallways[134]?.classrooms?.add(1008)
    hallways[134]?.classrooms?.add(1009)
    hallways[135]?.classrooms?.add(10607) //1060G
    hallways[136]?.classrooms?.add(1079)
    hallways[136]?.classrooms?.add(1080)
    hallways[137]?.classrooms?.add(1005)
    //hallways[138]?.classrooms?.add(1003) double entrance case
    hallways[139]?.classrooms?.add(1004)
    hallways[139]?.classrooms?.add(1003)
    hallways[140]?.classrooms?.add(10604) // 1060D
    hallways[141]?.classrooms?.add(1081)
    hallways[141]?.classrooms?.add(1078)
    hallways[141]?.classrooms?.add(1082)
    hallways[141]?.classrooms?.add(1083)
    hallways[142]?.classrooms?.add(1092) //elevators
    hallways[142]?.classrooms?.add(1002)
    hallways[142]?.classrooms?.add(1091)
    hallways[144]?.classrooms?.add(10605) //1060E
    hallways[146]?.classrooms?.add(1088)
    //hallways[148]?.classrooms?.add(1088) double entrance case
    //hallways[148]?.classrooms?.add(1085) double entrace case
    hallways[149]?.classrooms?.add(1107)
    hallways[150]?.classrooms?.add(1085)
    hallways[151]?.classrooms?.add(1106)
    hallways[152]?.classrooms?.add(1089)
    hallways[153]?.classrooms?.add(1001)
}

fun createClassroomToHallwayMap(hallways: Map<Int, HallwayNode>) {
    hallways.forEach { (hallwayId, hallway) ->
        hallway.classrooms.forEach { classroomId ->
            classroomToHallwayMap[classroomId] = hallwayId
        }
    }
}
