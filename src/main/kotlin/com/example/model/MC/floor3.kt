fun createHallways(): Map<Int, HallwayNode> {
    return (301..335).associateWith { HallwayNode(nodeId = it) }
}

fun connectClassroomsToHallways(hallways: Map<Int, HallwayNode>) {
    hallways[301]?.classrooms?.addAll(listOf(30121, 3012, 3072, 3089))
    hallways[302]?.classrooms?.addAll(listOf(3011, 30901, 3071))
    hallways[305]?.classrooms?.addAll(listOf(30151, 3016, 3091))
    hallways[306]?.classrooms?.add(3091)
    hallways[307]?.classrooms?.addAll(listOf(3074, 3093, 3094))
    hallways[308]?.classrooms?.add(3017)
    hallways[309]?.classrooms?.addAll(listOf(3068, 3066, 3067, 3065))
    hallways[310]?.classrooms?.addAll(listOf(3010, 30091))
    hallways[311]?.classrooms?.addAll(listOf(301811, 302210, 30))
    hallways[312]?.classrooms?.addAll(listOf(3062, 3064))
    hallways[313]?.classrooms?.addAll(listOf(3060, 3059, 3058))
    hallways[316]?.classrooms?.addAll(listOf(3055, 3054, 3053, 3056))
    hallways[317]?.classrooms?.addAll(listOf(3029, 3030))
    hallways[318]?.classrooms?.addAll(listOf(3051, 3052, 3050))
    hallways[320]?.classrooms?.addAll(listOf(3031, 3033))
    hallways[321]?.classrooms?.addAll(listOf(3047, 3048, 3045, 3046))
    hallways[322]?.classrooms?.addAll(listOf(3044, 3043))
    hallways[323]?.classrooms?.add(3034)
    hallways[324]?.classrooms?.add(3041)
    hallways[325]?.classrooms?.add(3035)
    hallways[326]?.classrooms?.addAll(listOf(3035, 3036, 3038))
    hallways[327]?.classrooms?.addAll(listOf(3082, 3083, 3069))
    hallways[328]?.classrooms?.add(3080)
    hallways[329]?.classrooms?.add(30842)
    hallways[330]?.classrooms?.add(3070)
    hallways[333]?.classrooms?.add(3096)
    hallways[335]?.classrooms?.addAll(listOf(3075, 3095))
}
