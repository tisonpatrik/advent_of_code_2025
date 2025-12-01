data class Instructions(val direction: Char, val distance: Int)

fun main() {
    val listOfInstructions: List<Instructions> = readInput("Day01")
        .filter { it.isNotBlank() }
        .map { i ->
            val direction = i[0]
            val distance = i.drop(1).trim().toInt()
            Instructions(direction, distance)
        }

    val dialSize = 100
    val targetNumber = 0

    var currentPosition = 50
    var targetHitCount = 0

    for (inst in listOfInstructions) {
        val step = if (inst.direction == 'R') 1 else -1
        repeat(inst.distance) {
            currentPosition = (currentPosition + step).mod(dialSize)
            if (currentPosition == targetNumber) {
                targetHitCount++

            }
        }
    }
    println(targetHitCount)
}