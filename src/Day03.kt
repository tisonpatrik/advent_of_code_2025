data class Bank(val batteries: List<Int>)

fun main() {
    val bank: List<Bank> = readInput("Day03")
        .filter { it.isNotBlank() }
        .map { line ->
            val batteriesList = line.map { char -> char.digitToInt() }
            Bank(batteriesList)
        }

    val collectedValues = mutableListOf<Long>()

    val digitsToFind = 12

    for (battery in bank) {
        val list = battery.batteries
        var currentSearchIndex = 0
        val resultBuilder = StringBuilder()

        for (i in 0 until digitsToFind) {

            val remainingNeeded = digitsToFind - 1 - i
            val searchLimit = list.size - remainingNeeded

            var maxDigit = -1
            var maxDigitIndex = -1

            for (j in currentSearchIndex until searchLimit) {
                val digit = list[j]
                if (digit > maxDigit) {
                    maxDigit = digit
                    maxDigitIndex = j

                    if (digit == 9) break
                }
            }
            resultBuilder.append(maxDigit)
            currentSearchIndex = maxDigitIndex + 1
        }

        val finalNumber = resultBuilder.toString().toLong()
        collectedValues.add(finalNumber)
    }

    println(collectedValues.sum())
}