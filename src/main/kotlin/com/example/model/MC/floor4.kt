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
    return (400..458).associateWith { HallwayNode(nodeId = it) }
}

fun connectClassroomsToHallways(hallways: Map<Int, HallwayNode>) {
    hallways[400]?.classrooms?.addAll(listOf(4130, 4110))
    hallways[401]?.classrooms?.addAll(listOf(4107, 4108, 4109))
    hallways[402]?.classrooms?.addAll(listOf(40186))
    hallways[403]?.classrooms?.addAll(listOf(4018))
    hallways[456]?.classrooms?.addAll(listOf(40196, 40195))
    hallways[457]?.classrooms?.addAll(listOf(40194, 40197, 40193))
    hallways[458]?.classrooms?.addAll(listOf(40192, 4022, 40191))
    hallways[404]?.classrooms?.addAll(listOf(4020))
    hallways[405]?.classrooms?.addAll(listOf(4021))
    hallways[406]?.classrooms?.addAll(listOf(4022))
    hallways[408]?.classrooms?.addAll(listOf(4022, 4021))
    hallways[409]?.classrooms?.addAll(listOf(4131, 4111, 4118)) //4111 are stairs
    hallways[410]?.classrooms?.addAll(listOf(4114, 4115, 40000)) // 40000 is dc link, 4114 is bathroom, 4115 is elevator
    hallways[411]?.classrooms?.addAll(listOf(4016, 4017))
    hallways[412]?.classrooms?.addAll(listOf(4023))
    hallways[413]?.classrooms?.addAll(listOf(4014))
    hallways[414]?.classrooms?.addAll(listOf(4024))
    hallways[415]?.classrooms?.addAll(listOf(4013, 4012, 40201, 4020))
    hallways[416]?.classrooms?.addAll(listOf(4026))
    hallways[417]?.classrooms?.addAll(listOf(4010, 4011))
    hallways[418]?.classrooms?.addAll(listOf(4027, 4028))
    hallways[419]?.classrooms?.addAll(listOf(4009))
    hallways[420]?.classrooms?.add(4029)
    hallways[421]?.classrooms?.addAll(listOf(4008, 4058))
    hallways[422]?.classrooms?.addAll(listOf(4059))
    hallways[423]?.classrooms?.addAll(listOf(4058, 4060))
    hallways[424]?.classrooms?.addAll(listOf(4059, 4061))
    hallways[425]?.classrooms?.add(listOf(4060))
    hallways[426]?.classrooms?.addAll(listOf(4062, 4063, 4061))
    hallways[427]?.classrooms?.addAll(listOf(4063, 4064))
    hallways[428]?.classrooms?.addAll(listOf(4030))
    hallways[429]?.classrooms?.addAll(listOf(4007, 4006))
    hallways[430]?.classrooms?.addAll(listOf(4031, 4032))
    hallways[431]?.classrooms?.addAll(listOf(4005, 4004))
    hallways[432]?.classrooms?.addAll(listOf(4033, 4034))
    hallways[433]?.classrooms?.addAll(listOf(4003))
    hallways[434]?.classrooms?.addAll(listOf(4035, 4036))
    hallways[436]?.classrooms?.add(4039)
    hallways[437]?.classrooms?.add(4037)
    hallways[438]?.classrooms?.addAll(listOf(4104, 4103))
    hallways[439]?.classrooms?.addAll(listOf(4101)) //4101 are stairs
    hallways[440]?.classrooms?.addAll(listOf(4045))
    hallways[441]?.classrooms?.addAll(listOf(4046))
    hallways[442]?.classrooms?.addAll(listOf(4044, 4042, 4045))
    hallways[443]?.classrooms?.addAll(listOf(4042, 4043, 4040, 4124))
    hallways[444]?.classrooms?.add(4041)
    hallways[445]?.classrooms?.add(4041)
    hallways[446]?.classrooms?.addAll(listOf(4119, 4040))
    hallways[447]?.classrooms?.addAll(listOf(4048, 4049))
    hallways[448]?.classrooms?.addAll(listOf(4122)) //4122 are stairs
    hallways[449]?.classrooms?.addAll(listOf(4050, 4051, 40511))
    hallways[450]?.classrooms?.addAll(listOf(40512, 4052, 4053))
    hallways[451]?.classrooms?.addAll(listOf(4069, 4068, 4067, 4066))
    hallways[452]?.classrooms?.addAll(listOf(4066, 4065))
    hallways[454]?.classrooms?.addAll(listOf(4055, 4054))
    hallways[455]?.classrooms?.addAll(listOf(40551, 4057, 4056, 40571))
}

fun connectHallways(hallways: Map<Int, HallwayNode>) {
   hallways[400]?.apply {
       south = hallways[402]
   }
   hallways[401]?.apply {
       east = hallways[402]
       south = hallways[411]
   }
   hallways[402]?.apply {
       west = hallways[401]
       east = hallways[403]
       north = hallways[400]
   }
   hallways[403]?.apply {
       west = hallways[402]
       east = hallways[404]
       north = hallways[456]
   }
   hallways[404]?.apply {
       west = hallways[403]
       east = hallways[405]
   }
   hallways[405]?.apply {
       west = hallways[404]
       east = hallways[406]
   }
   hallways[406]?.apply {
       west = hallways[405]
       east = hallways[407]
       north = hallways[458]
   }
   hallways[407]?.apply {
       west = hallways[406]
       east = hallways[408]
   }
   hallways[408]?.apply {
       west = hallways[407]
       east = hallways[409]
   }
   hallways[409]?.apply {
       west = hallways[408]
       east = hallways[410]
       south = hallways[412]
   }
   hallways[410]?.apply {
       west = hallways[409]
   }
   hallways[411]?.apply {
       north = hallways[401]
       south = hallways[413]
   }
   hallways[412]?.apply {
       north = hallways[409]
       south = hallways[414]
   }
   hallways[413]?.apply {
       north = hallways[411]
       south = hallways[415]
   }
   hallways[414]?.apply {
       north = hallways[412]
       south = hallways[416]
   }
   hallways[415]?.apply {
       north = hallways[413]
       south = hallways[417]
   }
   hallways[416]?.apply {
       north = hallways[414]
       south = hallways[418]
   }
   hallways[417]?.apply {
       north = hallways[415]
       south = hallways[419]
   }
   hallways[418]?.apply {
       north = hallways[416]
       south = hallways[420]
   }
   hallways[419]?.apply {
       north = hallways[417]
       south = hallways[421]
   }
   hallways[420]?.apply {
       north = hallways[418]
       south = hallways[428]
   }
   hallways[421]?.apply {
       north = hallways[419]
       south = hallways[429]
   }
   hallways[422]?.apply {
       west = hallways[421]
       east = hallways[423]
   }
   hallways[423]?.apply {
       west = hallways[422]
       east = hallways[424]
   }
   hallways[424]?.apply {
       west = hallways[423]
       east = hallways[425]
   }
   hallways[425]?.apply {
       west = hallways[424]
       east = hallways[426]
   }
   hallways[426]?.apply {
       west = hallways[425]
       east = hallways[427]
   }
   hallways[427]?.apply {
       west = hallways[426]
       east = hallways[428]
   }
   hallways[428]?.apply {
       north = hallways[420]
       south = hallways[430]
   }
   hallways[429]?.apply {
       north = hallways[421]
       south = hallways[431]
   }
   hallways[430]?.apply {
       north = hallways[428]
       south = hallways[432]
   }
   hallways[431]?.apply {
       north = hallways[429]
       south = hallways[433]
   }
   hallways[432]?.apply {
       north = hallways[430]
       south = hallways[434]
   }
   hallways[433]?.apply {
       north = hallways[431]
       south = hallways[435]
   }
   hallways[434]?.apply {
       north = hallways[432]
       south = hallways[437]
   }
   hallways[435]?.apply {
       north = hallways[433]
       south = hallways[439]
   }
   hallways[436]?.apply {
       east = hallways[437]
       south = hallways[446]
   }
   hallways[437]?.apply {
       north = hallways[434]
       west = hallways[436]
   }
   hallways[438]?.apply {
       east = hallways[439]
   }
   hallways[439]?.apply {
       west = hallways[438]
       east = hallways[440]
   }
   hallways[440]?.apply {
       west = hallways[439]
       east = hallways[441]
   }
   hallways[441]?.apply {
       west = hallways[440]
       east = hallways[442]
   }
   hallways[442]?.apply {
       west = hallways[441]
       east = hallways[443]
       south = hallways[451]
   }
   hallways[443]?.apply {
       west = hallways[442]
       east = hallways[444]
   }
   hallways[444]?.apply {
       west = hallways[443]
       east = hallways[445]
   }
   hallways[445]?.apply {
       west = hallways[444]
       east = hallways[446]
   }

   hallways[446]?.apply {
       west = hallways[445]
   }
   hallways[447]?.apply {
       north = hallways[444]
       south = hallways[449]
   }
   hallways[448]?.apply {
       north = hallways[447]
       south = hallways[453]
   }
   hallways[449]?.apply {
       north = hallways[447]
       south = hallways[450]
   }
   hallways[450]?.apply {
       north = hallways[449]
       south = hallways[454]
   }
   hallways[451]?.apply {
       east = hallways[452]
       north = hallways[442]
   }
   hallways[452]?.apply {
       west = hallways[452]
       east = hallways[453]
   }
   hallways[453]?.apply {
       north = hallways[448]
       west = hallways[452]
   }
   hallways[454]?.apply {
       north = hallways[450]
       south = hallways[455]
   }
   hallways[455]?.apply {
       north = hallways[454]
   }
   hallways[456]?.apply {
       south = hallways[403]
       east = hallways[457]
   }
   hallways[457]?.apply {
       west = hallways[456]
       east = hallways[458]
   }
   hallways[458]?.apply {
       west = hallways[457]
       south = hallways[406]
   }
   
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