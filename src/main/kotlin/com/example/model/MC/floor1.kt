package com.example.model.MC

fun buildGraph(): Map<Int, HallwayNode> {
    // Create classroom nodes
    val classrooms = createClassrooms()

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
    connectClassroomsToHallways(hallways, classrooms)

    println("Classrooms connected")

    return hallways
}

fun createClassrooms(): Map<Int, ClassroomNode> {
    return mapOf(
        1001 to ClassroomNode(roomId = 1001),
        1003 to ClassroomNode(roomId = 1003),
        1007 to ClassroomNode(roomId = 1007),
        1004 to ClassroomNode(roomId = 1004),
        1005 to ClassroomNode(roomId = 1005),
        1006 to ClassroomNode(roomId = 1006),
        1008 to ClassroomNode(roomId = 1008),
        1009 to ClassroomNode(roomId = 1009),
        1010 to ClassroomNode(roomId = 1010),
        1011 to ClassroomNode(roomId = 1011),
        1012 to ClassroomNode(roomId = 1012),
        1013 to ClassroomNode(roomId = 1013),
        1014 to ClassroomNode(roomId = 1014),
        1015 to ClassroomNode(roomId = 1015),
        1016 to ClassroomNode(roomId = 1016),
        1017 to ClassroomNode(roomId = 1017),
        1018 to ClassroomNode(roomId = 1018),
        1020 to ClassroomNode(roomId = 1020),
        1021 to ClassroomNode(roomId = 1021),
        1022 to ClassroomNode(roomId = 1022),
        1023 to ClassroomNode(roomId = 1023),
        1024 to ClassroomNode(roomId = 1024),
        1025 to ClassroomNode(roomId = 1025),
        1026 to ClassroomNode(roomId = 1026),
        1027 to ClassroomNode(roomId = 1027),
        1028 to ClassroomNode(roomId = 1028),
        1029 to ClassroomNode(roomId = 1029),
        1030 to ClassroomNode(roomId = 1030),
        1031 to ClassroomNode(roomId = 1031),
        1032 to ClassroomNode(roomId = 1032),
        1033 to ClassroomNode(roomId = 1033),
        1034 to ClassroomNode(roomId = 1034),
        1035 to ClassroomNode(roomId = 1035),
        1036 to ClassroomNode(roomId = 1036),
        1037 to ClassroomNode(roomId = 1037),
        1038 to ClassroomNode(roomId = 1038),
        1039 to ClassroomNode(roomId = 1039),
        1040 to ClassroomNode(roomId = 1040),
        1041 to ClassroomNode(roomId = 1041),
        1042 to ClassroomNode(roomId = 1042),
        1043 to ClassroomNode(roomId = 1043),
        1044 to ClassroomNode(roomId = 1044),
        1045 to ClassroomNode(roomId = 1045),
        1046 to ClassroomNode(roomId = 1046),
        1048 to ClassroomNode(roomId = 1048),
        1049 to ClassroomNode(roomId = 1049),
        1052 to ClassroomNode(roomId = 1052),
        1056 to ClassroomNode(roomId = 1056),
        1058 to ClassroomNode(roomId = 1058),
        1059 to ClassroomNode(roomId = 1059),
        1061 to ClassroomNode(roomId = 1061),
        1063 to ClassroomNode(roomId = 1063),
        1065 to ClassroomNode(roomId = 1065),
        1066 to ClassroomNode(roomId = 1066),
        1068 to ClassroomNode(roomId = 1068),
        1070 to ClassroomNode(roomId = 1070),
        1071 to ClassroomNode(roomId = 1071),
        1072 to ClassroomNode(roomId = 1072),
        1073 to ClassroomNode(roomId = 1073),
        1074 to ClassroomNode(roomId = 1074),
        1076 to ClassroomNode(roomId = 1076),
        1077 to ClassroomNode(roomId = 1077),
        1078 to ClassroomNode(roomId = 1078),
        1079 to ClassroomNode(roomId = 1079),
        1080 to ClassroomNode(roomId = 1080),
        1081 to ClassroomNode(roomId = 1081),
        1082 to ClassroomNode(roomId = 1082),
        1084 to ClassroomNode(roomId = 1084),
        1085 to ClassroomNode(roomId = 1085),
        1088 to ClassroomNode(roomId = 1088),
        1089 to ClassroomNode(roomId = 1089),
        1092 to ClassroomNode(roomId = 1092),
        1107 to ClassroomNode(roomId = 1107),
        10601 to ClassroomNode(roomId = 10601),
        10602 to ClassroomNode(roomId = 10602),
        10603 to ClassroomNode(roomId = 10603),
        10604 to ClassroomNode(roomId = 10604),
        10605 to ClassroomNode(roomId = 10605),
        10607 to ClassroomNode(roomId = 10607),
        10608 to ClassroomNode(roomId = 10608)
    )
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
    
    globalDistances[hallways[109]!! to hallways[115]!!] = 1.5 * unit
    globalDistances[hallways[115]!! to hallways[109]!!] = 1.5 * unit
    
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

fun connectClassroomsToHallways(hallways: Map<Int, HallwayNode>, classrooms: Map<Int, ClassroomNode>) {
    // Example connections, adjust based on actual requirements
    hallways[101]?.classrooms?.add(classrooms[1001]!!)
    hallways[102]?.classrooms?.add(classrooms[1003]!!)
    hallways[103]?.classrooms?.add(classrooms[1007]!!)

    classrooms[1001]?.connectedHallway = hallways[101]
    classrooms[1003]?.connectedHallway = hallways[102]
    classrooms[1007]?.connectedHallway = hallways[103]
}
