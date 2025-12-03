data class Range(val start: Long, val end: Long)
fun main() {
    val rawInput = readInput("Day02").first()

    val ranges: List<Range> = rawInput.split(",")
        .map { rangeString ->
            val parts = rangeString.split("-")
            val start = parts[0].toLong()
            val end = parts[1].toLong()
            Range(start, end)
        }
    val invalidIds = mutableListOf<Long>()
    for (range in ranges)
        for (id in range.start..range.end) {
            if (isInvalid(id)) {
                invalidIds.add(id)
            }
        }
    println(invalidIds.sum())
}


fun isInvalid(id: Long): Boolean {
    val s = id.toString()
    val len = s.length

    for (patternLen in 1..len / 2) {

        // If length is 9, a pattern cannot be length 2 (9 % 2 != 0)
        if (len % patternLen != 0) {
            continue
        }
        val pattern = s.take(patternLen)
        val repeatsNeeded = len / patternLen
        val reconstructed = pattern.repeat(repeatsNeeded)

        if (reconstructed == s) {
            return true
        }
    }

    return false
}