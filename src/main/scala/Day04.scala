import java.time.{Duration, Instant, ZonedDateTime}

/** Advent of Code 2015 Day 4
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 4 puzzles.
 */
class Day04:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run: Unit = Day04.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day04.puzzleTitle}"

end Day04

object Day04 {

    // created 9/23/2023
    // https://adventofcode.com/2015/day/3

    val puzzleTitle = "Day 4: The Ideal Stocking Stuffer"
    val day = "Day04"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day04 = {
        val p = new Day04()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day04 = {
        var p = new Day04()
        p.title = title
        p.runType = runType
        p
    }

    private def runPuzzle(title: String, runType: Int): Unit = {

        println(s"--- $title ---")
        println(s"--- $puzzleTitle ---")
        if (runType == 1)
            println("--- USING TEST DATA ---\n")
        else
            println("--- USING REAL INPUT DATA ---\n")

        println(s"Start Timestamp ${ZonedDateTime.now()}")

        // Commmon to both parts
        val input = "ckczppom"


        // Part One
        println(s"Part 1: find Santa the lowest positive number (no leading zeroes: 1, 2, 3, ...) that produces such a hash.")
        val p1T0 = Instant.now()



        val delta1 = Duration.between(p1T0, Instant.now())
        println(s"Part 1 elased time approx ${delta1.toMillis} milliseconds\n")


        // Part Two
        println(s"Part 2: TBD")
        val p2T0 = Instant.now()




        val delta2 = Duration.between(p2T0, Instant.now())
        println(f"Part 2 elapsed time approx ${delta2.toMillis} milliseconds")

        println(s"\nEnd at ${ZonedDateTime.now()}")

        // errata...for visualation with Excel chart

    }
}
