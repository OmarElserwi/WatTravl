import java.util.PriorityQueue

ata class HallwayNode(
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

fun dijkstra(start: HallwayNode): Map<HallwayNode, Int> {
    val distances = mutableMapOf<HallwayNode, Int>()
    val priorityQueue = PriorityQueue<Pair<HallwayNode, Int>>(compareBy { it.second })

    priorityQueue.add(start to 0)
    distances[start] = 0

    while (priorityQueue.isNotEmpty()) {
        val (currentNode, currentDistance) = priorityQueue.poll()

        if (currentDistance > distances.getOrDefault(currentNode, Int.MAX_VALUE)) continue

        listOfNotNull(currentNode.north, currentNode.south, currentNode.east, currentNode.west).forEach { neighbor ->
            val distance = currentNode.distances[neighbor] ?: return@forEach
            val newDistance = currentDistance + distance
            if (newDistance < distances.getOrDefault(neighbor, Int.MAX_VALUE)) {
                distances[neighbor] = newDistance
                priorityQueue.add(neighbor to newDistance)
            }
        }
    }

    return distances
}

fun main() {
    // Create classroom nodes
    val classroom1 = ClassroomNode(roomId = "C101")
    val classroom2 = ClassroomNode(roomId = "C102")
    val classroom3 = ClassroomNode(roomId = "C103")

    // Create hallway nodes
    val hallway1 = HallwayNode(nodeId = "H1")
    val hallway2 = HallwayNode(nodeId = "H2")
    val hallway3 = HallwayNode(nodeId = "H3")

    // Connect hallways
    hallway1.east = hallway2
    hallway2.west = hallway1
    hallway2.south = hallway3
    hallway3.north = hallway2

    // Set distances between hallways
    hallway1.distances[hallway2] = 10
    hallway2.distances[hallway1] = 10
    hallway2.distances[hallway3] = 15
    hallway3.distances[hallway2] = 15

    // Connect classrooms to hallways
    hallway1.classrooms.add(classroom1)
    hallway2.classrooms.add(classroom2)
    hallway3.classrooms.add(classroom3)

    // Connect classrooms to hallways
    classroom1.connectedHallway = hallway1
    classroom2.connectedHallway = hallway2
    classroom3.connectedHallway = hallway3

    // Run Dijkstra's algorithm from hallway1
    val distances = dijkstra(hallway1)
    distances.forEach { (hallway, distance) ->
        println("Distance from ${hallway1.nodeId} to ${hallway.nodeId}: $distance")
    }
}