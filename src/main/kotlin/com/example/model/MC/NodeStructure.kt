package com.example.model.MC

data class HallwayNode(
    val nodeId: Int,                        // Identifier for the hallway node
    var north: HallwayNode? = null,         // North neighboring hallway node
    var south: HallwayNode? = null,         // South neighboring hallway node
    var east: HallwayNode? = null,          // East neighboring hallway node
    var west: HallwayNode? = null,          // West neighboring hallway node
    val classrooms: MutableList<Int> = mutableListOf()  // Connected classrooms
) {
    override fun hashCode(): Int {
        return nodeId
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is HallwayNode) return false
        return nodeId == other.nodeId
    }
}

val globalDistances = mutableMapOf<Pair<HallwayNode, HallwayNode>, Double>()

// No need to print the map structure anymore, focusing on Dijkstra's algorithm
