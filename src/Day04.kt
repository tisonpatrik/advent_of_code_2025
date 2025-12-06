

data class Grid(
    val width: Int,
    val height: Int,
    private val cells: BooleanArray
) {
    operator fun get(x: Int, y: Int): Boolean {
        if (x !in 0 until width || y !in 0 until height) return false
        return cells[y * width + x]
    }

    fun turnOff(x: Int, y: Int) {
        if (x in 0 until width && y in 0 until height) {
            cells[y * width + x] = false
        }
    }

    fun shouldBeRemoved(x: Int, y: Int): Boolean {
        if (!this[x, y]) return false

        var activeNeighbors = 0
        for (dy in -1..1) {
            for (dx in -1..1) {
                if (dx == 0 && dy == 0) continue

                if (this[x + dx, y + dy]) {
                    activeNeighbors++
                }

                if (activeNeighbors >= 4) return false
            }
        }
        return true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Grid

        if (width != other.width) return false
        if (height != other.height) return false
        if (!cells.contentEquals(other.cells)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = width
        result = 31 * result + height
        result = 31 * result + cells.contentHashCode()
        return result
    }
}

fun main() {
    val lines = readInput("Day04").filter { it.isNotBlank() }

    val height = lines.size
    val width = lines.firstOrNull()?.length ?: 0

    val cells = BooleanArray(width * height)
    var index = 0
    for (line in lines) {
        for (char in line) {
            cells[index++] = (char == '@')
        }
    }

    val grid = Grid(width, height, cells)

    var totalRemovedCount = 0

    while (true) {

        val toRemove = mutableListOf<Pair<Int, Int>>()

        for (y in 0 until grid.height) {
            for (x in 0 until grid.width) {
                if (grid.shouldBeRemoved(x, y)) {
                    toRemove.add(x to y)
                }
            }
        }

        if (toRemove.isEmpty()) {
            break
        }

        totalRemovedCount += toRemove.size

        for ((rx, ry) in toRemove) {
            grid.turnOff(rx, ry)
        }


    }

    println("Celkově odstraněno buněk: $totalRemovedCount")
}