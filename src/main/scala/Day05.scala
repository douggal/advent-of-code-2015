import java.io.File
import java.time.{Duration, Instant, ZonedDateTime}
import scala.collection.mutable.ArrayBuffer

/** Advent of Code 2015 Day 5
 *
 * Defines a class, its companion object and runner method for
 * the AoC Day 5 puzzles.
 */
class Day05:

    var title: String = "Default"
    var runType: Int = 1 // Default to test data

    def run = Day05.runPuzzle(title, runType)

    override def toString: String = s"Class ${Day05.puzzleTitle}"

end Day05

object Day05 {

    // created 9/24/2023
    // https://adventofcode.com/2015/day/5

    val puzzleTitle = "Day 5: Doesn't He Have Intern-Elves For This?"
    val day = "Day05"
    private val testData: String = s"${day}TestData.txt"
    private val realData: String = s"${day}Input.txt"

    // a one-arg constructor
    def apply(title: String): Day05 = {
        var p = new Day05()
        p.title = title
        p
    }

    // a two-arg constructor
    def apply(title: String, runType: Int): Day05 = {
        var p = new Day05()
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

        // Puzzle Input Data File
        val filename = if (runType == 1) testData else realData

        val readInputData = () => {
            val f = java.nio.file.Paths.get("./input").toAbsolutePath().toString
            val source = io.Source.fromFile(s"$f/$filename", "UTF-8")
            for {
                line <- source.getLines().toVector
            } yield
                line
        }

        val input = readInputData()
        /*for (line <- input) {
            println(line)
        }*/

        println(s"Start Timestamp ${ZonedDateTime.now()}")

        println("\nData Quality Control:")
        println(s"Input file name: $filename")
        println(s"Each line is a: ${input.getClass}")
        println(s"Number lines: ${input.length}")
        println(s"Number items per line: ${input.head.count(_ => true)}")
        println(s"Input first line: ${input.head.take(25)}")
        if (input.size > 1) println(s"Input last line: ${input.tail.last}")
        println("End QC on input file\n")

        // Commmon to both parts

        // Part One
        println(s"Part 1: How many strings are nice?")
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
