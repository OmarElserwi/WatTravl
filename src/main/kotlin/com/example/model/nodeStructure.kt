data class HallwayNode(
    val nodeId: Int,                        // Identifier for the hallway node
    var north: HallwayNode? = null,            // North neighboring hallway node
    var south: HallwayNode? = null,            // South neighboring hallway node
    var east: HallwayNode? = null,             // East neighboring hallway node
    var west: HallwayNode? = null,             // West neighboring hallway node
    val classrooms: MutableList<ClassroomNode> = mutableListOf() // Connected classroom nodes
    val distances: MutableMap<HallwayNode, Int> = mutableMapOf()  
)

data class ClassroomNode(
    val roomId: Int,       // Identifier for the classroom
    var connectedHallway: HallwayNode? = null // Connected hallway node
)